#!/bin/bash

if [ $# -ne 4 ]
then
	echo "Usage: $0 directory dmgfile volname mountdir"
	exit 1
fi
			
appdir_size=`du -sk $1 | cut -f1`
background_size=`du -sk Mac/background.tiff | cut -f1`
dsstore_size=`du -sk Mac/DS_Store.tmp | cut -f1`
volicon_size=`du -sk Mac/VolumeIcon.icns | cut -f1`
image_size=`expr $appdir_size + $background_size + $dsstore_size + $volicon_size + 250`
echo "$1: ${appdir_size}K"

dd if=/dev/zero of=$2 bs=1024 count=$image_size status=noxfer
mkfs.hfsplus -v "$3" $2

echo "Creating mount location $4"
mkdir $4
echo "Mounting the image $2 in $4"
mount -o loop -t hfsplus $2 $4

echo "Adding Applications link, along with assorted image and layout data"
ln -s /Applications $4/Applications
cp --no-preserve=ownership Mac/background.tiff $4/.background.tiff
cp --no-preserve=ownership Mac/VolumeIcon.icns $4/.VolumeIcon.icns
cp --no-preserve=ownership Mac/DS_Store.tmp $4/.DS_Store
cp --no-preserve=ownership $1/Contents/Resources/Java/license.txt $4/License.txt
rm Mac/DS_Store.tmp

echo "Copying the application $1 into the image $2, mounted at $4"
cp -a --no-preserve=ownership $1 $4

echo "Umounting the image from $4"
umount $4
echo "Removing mount location $4"
rmdir $4
