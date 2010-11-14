#!/bin/bash

if [ $# -ne 3 ]
then
	echo "Usage: $0 directory dmgfile mountdir"
	exit 1
fi
			
appdir_size=`du -sk $1 | cut -f1`
background_size=`du -sk Mac/background.tiff | cut -f1`
dsstore_size=`du -sk Mac/DS_Store | cut -f1`
volicon_size=`du -sk Mac/VolumeIcon.icns | cut -f1`
image_size=`expr $appdir_size + $background_size + $dsstore_size + $volicon_size + 250`
echo "$1: ${appdir_size}K"

dd if=/dev/zero of=$2 bs=1024 count=$image_size status=noxfer
mkfs.hfsplus -v Anathema $2

echo "Creating mount location $3"
mkdir $3
echo "Mounting the image $2 in $3"
mount -o loop -t hfsplus $2 $3

echo "Adding Applications link, along with assorted image and layout data"
ln -s /Applications $3/Applications
cp Mac/background.tiff $3/.background.tiff
cp Mac/VolumeIcon.icns $3/.VolumeIcon.icns
cp Mac/DS_Store $3/.DS_Store
cp $1/Contents/Resources/Java/license.txt $3/License.txt

echo "Copying the application $1 into the image $2, mounted at $3"
cp -a $1 $3

echo "Umounting the image from $3"
umount $3
echo "Removing mount location $3"
rmdir $3
