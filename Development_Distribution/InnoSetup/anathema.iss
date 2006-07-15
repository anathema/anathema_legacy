; Inno Setup uses absolute paths to each of the installers files (eg. C:\A-Folder\B-File.xxx).
; SourceDir can bypass setting some of that, if you set it to the directory where "Anathema" (see diagram below) resides.
; In this script SourceDir is set to "C:\Documents and Settings\Administrator\Desktop\workspace",
; any of the following folders requiring a source now prepend this the source's location.
;
; --DIRECTORY DIAGRAM
;
; SourceDir
;     |- anathema
;          |- files
;          |    |- icons
;          |    |- images
;          |    |- application (anathema-developer-v0.11.zip + extra files)
;          |- scripts
;          |    |- inno
;          |    |- nsis
;          |    |- launch4j
;          |- executables
;               |- inno
;               |-nsis
;
; NOTE:  It will be necessary to replace "Standard User" with your user name.
;

[Setup]
; DYNAMIC COMPILER RELATED
OutputBaseFilename=anathema-setup-v0.12
OutputManifestFile=anathema-setup-v0.12.txt
VersionInfoTextVersion=v0.11
VersionInfoVersion=0.11.0.0
; DYNAMIC INSTALLER RELATED
AppVersion=v0.12
AppVerName=Anathema v0.12
OutputDir=..\
; To compile the Anathema installer correctly, set the following command to which ever directory contains "Anathema".
SourceDir=..\

; STATIC COMPILER RELATED
Compression=zip/9
; INSTALLER RELATED
VersionInfoCopyright=2004-2006 by Sandra Sieroux and Urs Reupke
AppName=Anathema
AppPublisher=Anathema Team
AppPublisherURL=http://anathema.sourceforge.net
AppSupportURL=http://sourceforge.net/tracker/?group_id=122320&atid=693109
AppUpdatesURL=http://sourceforge.net/project/showfiles.php?group_id=122320
AppReadmeFile={app}\readme.txt
DefaultDirName={pf}\Anathema
DefaultGroupName=Anathema
InfoBeforeFile=innosetup\installer-readme.txt
InfoAfterFile=build\files\readme.txt
LanguageDetectionMethod=uilanguage
LicenseFile=build\files\license.txt
TimeStampsInUTC=yes
; COSMETIC COMMANDS
SetupIconFile=innosetup\installer.ico
WizardImageFile=innosetup\anathema-wizard.bmp
WizardImageStretch=no
WizardImageBackColor=clBlack
WizardSmallImageFile=innosetup\anathema-wizard-small.bmp
;
; The following lines relate to the background window for the installer.
; Without "WindowVisible" being enabled none of the rest function.
; WindowVisible=yes
;   AppCopyright=Copyright (C) 2004-2006 by Sandra Sieroux and Urs Reupke.
;   BackColor=clYellow
;   BackColor2=clBlack

[Languages]
; ENGLISH LANGUAGE
  Name: english; MessagesFile: compiler:Default.isl
; SPANISH LANGUAGE
  Name: spanish; MessagesFile: compiler:Languages\Spanish.isl; LicenseFile: anathema\files\application\doc\spanish\license_es.txt; InfoBeforeFile: anathema\files\application\installer-readme.txt; InfoAfterFile: anathema\files\application\doc\spanish\readme_es.txt

[Tasks]
Name: desktopicon; Description: {cm:CreateDesktopIcon}; GroupDescription: {cm:AdditionalIcons}; Flags: unchecked
Name: quicklaunchicon; Description: {cm:CreateQuickLaunchIcon}; GroupDescription: {cm:AdditionalIcons}; Flags: unchecked

[Files]
; NOTE: Don't use "Flags: ignoreversion" on any shared system files
;
; COMMON
Source: build\anathema.exe; DestDir: {app}; Components: main; Flags: ignoreversion
Source: build\anathema.jar; DestDir: {app}; Components: main; Flags: ignoreversion
Source: build\plugins\*.jar; DestDir: {app}\plugins; Components: music; Flags: ignoreversion
;Source: anathema\files\application\data\*; DestDir: {app}\data; Components: main; Flags: ignoreversion recursesubdirs createallsubdirs
Source: build\core\libraries\*; DestDir: {app}\lib; Components: main; Flags: ignoreversion recursesubdirs createallsubdirs
Source: build\charmcascades\libraries\*; DestDir: {app}\plugins\libraries\; Components: main; Flags: ignoreversion recursesubdirs createallsubdirs
; Eventually, I want to be able to replace the following two files with locale specific versions.
Source: InnoSetup\installer-readme.txt; DestDir: {app}; Components: main; Flags: ignoreversion

Source: buildlib\*; DestDir: {app}\modules\lib; Components: music; Flags: ignoreversion recursesubdirs createallsubdirs

; [Code] files
Source: anathema\files\images\Green-PDF.bmp; Flags: dontcopy
Source: anathema\files\images\Green-JRE.bmp; Flags: dontcopy
Source: anathema\files\images\Red-PDF.bmp; Flags: dontcopy
Source: anathema\files\images\Red-JRE.bmp; Flags: dontcopy
; ENGLISH LANGUAGE
  Source: anathema\files\application\FAQ.txt; DestDir: {app}; Components: main; Languages: english; Flags: ignoreversion
  Source: anathema\files\application\license.txt; DestDir: {app}; Components: main; Languages: english; Flags: ignoreversion
  Source: anathema\files\application\data\nature_instructions.txt; DestDir: {app}\data; Components: main; Languages: english; Flags: ignoreversion recursesubdirs createallsubdirs
  Source: anathema\files\application\readme.txt; DestDir: {app}; Components: main; Languages: english; Flags: ignoreversion
; isreadme <-- This flag can be appended to the above command to place a "view readme" box at the end of the installer.  All things considered, this is a bit overkill at this point.
; The following commands are waiting for a language specific versions.
;  Source: anathema\files\application\installer-readme.txt; DestDir: {app}; Components: main; Languages: english; Flags: ignoreversion
;  Source: anathema\files\application\versions.txt; DestDir: {app}; Components: main; Languages: english; Flags: ignoreversion
;
; SPANISH LANGUAGE
  Source: anathema\files\application\doc\Spanish\FAQ_es.txt; DestDir: {app}; DestName: FAQ.txt; Languages: spanish; Components: main; Flags: ignoreversion
  Source: anathema\files\application\doc\Spanish\license_es.txt; DestDir: {app}; DestName: license.txt; Languages: spanish; Components: main; Flags: ignoreversion
  Source: anathema\files\application\data\nature_instructions_es.txt; DestDir: {app}\data; DestName: nature_instructions.txt; Components: main; Languages: spanish; Flags: ignoreversion recursesubdirs createallsubdirs
  Source: anathema\files\application\doc\Spanish\readme_es.txt; DestDir: {app}; DestName: readme.txt; Languages: spanish; Components: main; Flags: ignoreversion
; isreadme <-- This flag can be appended to the above command to place a "view readme" box at the end of the installer.  All things considered, this is a bit overkill at this point.
; The following commands are waiting for a language specific versions.
;  Source: anathema\files\application\doc\Spanish\installer-readme_es.txt; DestDir: {app}; DestName: installer-readme.txt; Languages: spanish; Components: main; Flags: ignoreversion
;  Source: anathema\files\application\doc\Spanish\versions_es.txt; DestDir: {app}; DestName: versions.txt; Languages: spanish; Components: main; Flags: ignoreversion

[CustomMessages]
; ENGLISH LANGUAGE
;   [TYPES]
  Anathema_Compact=Compact Installation
  Anathema_Custom=Custom Installation
  Anathema_Full=Full Installation
;    [COMPONENTS]
  Anathema_DeveloperFiles=Developer Files
  Anathema_MusicPlugin=Music Plugin
;    [ICONS]
  Anathema_Comment=Anathema: A nascent approach to harmonic Exalted management.
  Anathema_License=License
  Anathema_ReadMe=ReadMe
  Anathema_VersionHistory=Version History
  Anathema_InstallerReadMe=Readme-Installer
  Anathema_Natures=Readme-Natures
;    [CODE]
  Anathema_checkCaption=Software Check
  Anathema_checkDescription=Additional software is required to fully utilize Anathema.
  Anathema_JRE1=Since it is written in Java, Anathema requires a JRE to run.
  Anathema_JRE3=It can be obtained at:  http://java.sun.com/getjava
  Anathema_Acrobat1=You must obtain Acrobat if you want to print.
  Anathema_Acrobat3=See:  http://www.adobe.com/products/acrobat/readstep2.html
; SPANISH LANGUAGE
;    [TYPES]
  spanish.Anathema_Compact=Instalación Compacta
  spanish.Anathema_Custom=Instalación Personalizada
  spanish.Anathema_Full=Instalación Completa
;    [COMPONENTS]
  spanish.Anathema_MusicPlugin=Plugin de Música
  spanish.Anathema_DeveloperFiles=Archivos del revelador
;    [ICONS]
  spanish.Anathema_Comment=Anathema: Un acercamiento naciente a la gerencia Exalted armónica.
  spanish.Anathema_License=Licencia
  spanish.Anathema_ReadMe=Leeme
  spanish.Anathema_Natures=Leeme-Naturalezas
;  spanish.Anathema_VersionHistory=
;  spanish.Anathema_InstallerReadMe=
;    [CODE]
  spanish.Anathema_checkCaption=Examinar el software
  spanish.Anathema_checkDescription=El software adicional se puede requerir para utilizar completamente Anathema.
  spanish.Anathema_JRE1=Puesto que se escribe en Java, el anatema requiere un JRE funcionar.
  spanish.Anathema_JRE3=Puede ser obtenido en:  http://java.sun.com/getjava
  spanish.Anathema_Acrobat1=Debes obtener a Acrobat si deseas imprimir.
  spanish.Anathema_Acrobat3=Ver:  http://www.adobe.com/products/acrobat/readstep2

[Types]
; COMMON
Name: "compact"; Description: {cm:Anathema_Compact};
Name: "custom"; Description: {cm:Anathema_Custom}; Flags: iscustom
Name: "full"; Description: {cm:Anathema_Full};

[Components]
; COMMON
Name: main; Description: Anathema; Types: full compact custom; Flags: fixed
Name: music; Description: {cm:Anathema_MusicPlugin}; Types: full custom
Name: developer; Description: {cm:Anathema_DeveloperFiles}; Types: full custom

[Registry]
; COMMON
Root: HKCU; Subkey: Software\JavaSoft\Prefs\anathema; Flags: uninsdeletekeyifempty
Root: HKCU; Subkey: Software\JavaSoft\Prefs\anathema\system; ValueType: string; ValueName: /Force/Metal/Look/And/Feel; ValueData: false; Flags: uninsdeletevalue uninsdeletekeyifempty
Root: HKCU; Subkey: Software\JavaSoft\Prefs\anathema\character; ValueType: string; ValueName: /Ruleset; ValueData: /Core/Rules; Flags: uninsdeletevalue uninsdeletekeyifempty
; ENGLISH LANGUAGE
  Root: HKCU; Subkey: Software\JavaSoft\Prefs\anathema\system; ValueType: string; ValueName: /Locale; ValueData: /English; Languages: english; Flags: uninsdeletevalue
; SPANISH LANGUAGE
  Root: HKCU; Subkey: Software\JavaSoft\Prefs\anathema\system; ValueType: string; ValueName: /Locale; ValueData: /Spanish; Languages: spanish; Flags: uninsdeletevalue

[INI]
Filename: {app}\anathema.url; Section: InternetShortcut; Key: URL; String: http://anathema.sourceforge.net

[Icons]
; COMMON
Name: {group}\{cm:Anathema_DeveloperFiles}; Filename: {app}\src; Components: developer
Name: {group}\Anathema; Filename: {app}\anathema.exe; WorkingDir: {app}; Comment: {cm:Anathema_Comment}
Name: {group}\{cm:ProgramOnTheWeb,Anathema}; Filename: {app}\anathema.url; IconFilename: {app}\anathema.exe
Name: {group}\FAQ; Filename: {app}\FAQ.txt
Name: {group}\{cm:Anathema_License}; Filename: {app}\license.txt
Name: {group}\{cm:Anathema_ReadMe}; Filename: {app}\installer.txt
Name: {group}\{cm:Anathema_VersionHistory}; Filename: {app}\versions.txt
Name: {group}\{cm:Anathema_InstallerReadMe}; Filename: {app}\installer-readme.txt
Name: {group}\{cm:Anathema_Natures}; Filename: {app}\data\nature_instructions.txt
Name: {group}\{cm:UninstallProgram,Anathema}; Filename: {uninstallexe}
; OPTIONAL
Name: {userdesktop}\Anathema; Filename: {app}\anathema.exe; Tasks: desktopicon
Name: {userappdata}\Microsoft\Internet Explorer\Quick Launch\Anathema; Filename: {app}\anathema.exe; Tasks: quicklaunchicon

[Run]
Filename: {app}\anathema.exe; Description: {cm:LaunchProgram,Anathema}; Flags: nowait postinstall skipifsilent

[UninstallDelete]
Type: files; Name: {app}\anathema.url

[Code]

  var
    IMG_Acrobat: TBitmapImage;
    IMG_JRE: TBitmapImage;
    TXT_JRE: TNewStaticText;
    TXT_Acrobat: TNewStaticText;
    MEM_JRE: TMemo;
    MEM_Acrobat: TMemo;
    { PRA: Global Variables added to facilitate software detection }
    Bool_JRE: Boolean;
    Bool_Acrobat: Boolean;

  procedure app_check_Activate(Page: TWizardPage);
  begin
  end;

  function app_check_ShouldSkipPage(Page: TWizardPage): Boolean;
  begin
    Result := False;
  end;

  function app_check_BackButtonClick(Page: TWizardPage): Boolean;
  begin
    Result := True;
  end;

  function app_check_NextButtonClick(Page: TWizardPage): Boolean;
  begin
    Result := True;
  end;

  procedure app_check_CancelButtonClick(Page: TWizardPage; var Cancel, Confirm: Boolean);
  begin
  end;

  function app_check_CreatePage(PreviousPageId: Integer): Integer;
  var
    Page: TWizardPage;
    {PRA:  Each of the following was added to store the path to the necessary BMP files.}
    JRE_Green_BMP: String;  // Creates a String variable where the path will be stored.
    JRE_Red_BMP: String;
    PDF_Green_BMP: String;
    PDF_Red_BMP: String;
  begin
    {PRA:  Code added to get CheckMark_IMG and RedX_IMG to work. }
    JRE_Green_BMP := ExpandConstant('{tmp}\Green-JRE.bmp');  // Loads the "Path" into a variable.
      ExtractTemporaryFile(ExtractFileName(JRE_Green_BMP));  // Extracts file indicated in the JRE_Green_IMG to a temporary folder.
    JRE_Red_BMP := ExpandConstant('{tmp}\Red-JRE.bmp');
      ExtractTemporaryFile(ExtractFileName(JRE_Red_BMP));
    PDF_Green_BMP := ExpandConstant('{tmp}\Green-PDF.bmp');
      ExtractTemporaryFile(ExtractFileName(PDF_Green_BMP));
    PDF_Red_BMP := ExpandConstant('{tmp}\Red-PDF.bmp');
      ExtractTemporaryFile(ExtractFileName(PDF_Red_BMP));
    // On with the regularly scheduled (and computer generated) coding...
    Page := CreateCustomPage(
      PreviousPageId,
      ExpandConstant('{cm:Anathema_checkCaption}'),
      ExpandConstant('{cm:Anathema_checkDescription}')
    );

    { IMG_Acrobat }
    IMG_Acrobat := TBitmapImage.Create(Page);
    with IMG_Acrobat do
    begin
      Parent := Page.Surface;
      Left := ScaleX(10);
      Top := ScaleY(144);
      Width := ScaleX(50);
      Height := ScaleY(50);
    end;
    begin
    {PRA:  It might be noted, that I don't know if this has to be in its own "begin" section.
      Since, it works, though, I'm not messing with it. Oh, this actually puts the image into the box.}
      IMG_Acrobat.Bitmap.LoadFromFile(PDF_Green_BMP);  // Like with the Java Checker, this is the default value.
      if Bool_Acrobat = false then begin
        IMG_Acrobat.Bitmap.LoadFromFile(PDF_Red_BMP);  //  Unless, the "Global" variable Bool_Acrobat is "false".
      end
    end;

    { IMG_JRE }
    IMG_JRE := TBitmapImage.Create(Page);
//    IMG_JRE.OnClick := @IMG_JREOnClick;
    with IMG_JRE do
    begin
      Parent := Page.Surface;
      Left := ScaleX(10);
      Top := ScaleY(45);
      Width := ScaleX(50);
      Height := ScaleY(50);
    end;
    begin
    {PRA:  It might be noted, that I don't know if this has to be in its own "begin" section.
      Since, it works, though, I'm not messing with it.  Oh, this actually puts the image into the box.}
      IMG_JRE.Bitmap.LoadFromFile(JRE_Green_BMP);  // Like with the Java Checker, this is the default value.
      if Bool_JRE = false then begin
        IMG_JRE.Bitmap.LoadFromFile(JRE_Red_BMP);  //  Unless, the "Global" variable Bool_JRE is "false".
      end
    end;

    { TXT_JRE }
    TXT_JRE := TNewStaticText.Create(Page);
    with TXT_JRE do
    begin
      Parent := Page.Surface;
      Left := ScaleX(10);
      Top := ScaleY(10);
      Width := ScaleX(343);
      Height := ScaleY(21);
      Caption := 'Java Runtime Environment 5.0 or greater.';
      Font.Color := -16777208;
      Font.Height := ScaleY(20);
      Font.Name := 'Tahoma';
      Font.Style := [fsBold];
      ParentFont := False;
      TabOrder := 0;
    end;

    { TXT_Acrobat }
    TXT_Acrobat := TNewStaticText.Create(Page);
    with TXT_Acrobat do
    begin
      Parent := Page.Surface;
      Left := ScaleX(10);
      Top := ScaleY(109);
      Width := ScaleX(191);
      Height := ScaleY(21);
      Caption := 'Adobe Acrobat Reader.';
      Font.Color := -16777208;
      Font.Height := ScaleY(20);
      Font.Name := 'Tahoma';
      Font.Style := [fsBold];
      ParentFont := False;
      TabOrder := 1;
    end;

    { MEM_JRE }
    MEM_JRE := TMemo.Create(Page);
    with MEM_JRE do
    begin
      Parent := Page.Surface;
      Left := ScaleX(70);
      Top := ScaleY(41);
      Width := ScaleX(328);
      Height := ScaleY(58);
      Lines.Add(CustomMessage('Anathema_JRE1'));  // Links into the [CustomMessages] for an easier multi-lingual interface.
      Lines.Add('');
      Lines.Add(CustomMessage('Anathema_JRE3'));
      ReadOnly := True;
      TabOrder := 2;
    end;

    { MEM_Acrobat }
    MEM_Acrobat := TMemo.Create(Page);
    with MEM_Acrobat do
    begin
      Parent := Page.Surface;
      Left := ScaleX(70);
      Top := ScaleY(140);
      Width := ScaleX(328);
      Height := ScaleY(58);
      Lines.Add(CustomMessage('Anathema_Acrobat1'));  // Links into the [CustomMessages] for an easier multi-lingual interface.
      Lines.Add('');
      Lines.Add(CustomMessage('Anathema_Acrobat3'));
      ReadOnly := True;
      TabOrder := 3;
    end;


    with Page do
    begin
      OnActivate := @app_check_Activate;
      OnShouldSkipPage := @app_check_ShouldSkipPage;
      OnBackButtonClick := @app_check_BackButtonClick;
      OnNextButtonClick := @app_check_NextButtonClick;
      OnCancelButtonClick := @app_check_CancelButtonClick;
    end;

    Result := Page.ID;
  end;

  procedure InitializeWizard();
  begin
    app_check_CreatePage(wpWelcome);
  end;

{PRA:  The following 2 functions facilitate software detection.}

//This function reads "Current Version" from the registry and passes it back to the controlling function.

function getJavaVersion(): String;
  var
    javaVersion: String;
  begin
    javaVersion := '';
    RegQueryStringValue(HKLM, 'SOFTWARE\JavaSoft\Java Runtime Environment', 'CurrentVersion', javaVersion);
    Result := javaVersion;
  end;

//Acrobat and Java software detector.

{It should be noted that both of the following functions can be fooled. Bool_JRE can be fooled if the minor version is greater than 10.  eg. 2.11
 Bool_Acrobat can be fooled if Acrobat Reader doesn't remove all it's registry entries.}
 
function InitializeSetup(): Boolean;
  begin
    Bool_JRE := true; // This is the default value, if all other checks fail.
    Bool_Acrobat := RegKeyExists(HKEY_CURRENT_USER, 'Software\Adobe\Acrobat Reader'); // Check if Acrobat is installed.
    Result := true;  // Without this, the installer will exit because "InitializeSetup" is True (Start) or False (Stop).
      if Length( getJavaVersion() ) = 0 then begin
        Bool_JRE := false;
      end
    else begin
      if (getJavaVersion()) < '1.5' then begin
        Bool_JRE := false;
      end;
    end;
  end;

{PRA:  When functional these should open URL's to their respective sites when a user clicks on the image. }
{
procedure IMG_JREOnClick(Sender: TObject);
  var
    ErrorCode: Integer;
  begin
    ShellExec('open', 'http://java.sun.com/getjava', '', '', SW_SHOWNORMAL, ewNoWait, ErrorCode);
  end;

procedure IMG_AcrobatOnClick(Sender: TObject);
  var
    ErrorCode: Integer;
  begin
    ShellExec('open', 'http://www.adobe.com/products/acrobat/readstep2', '', '', SW_SHOWNORMAL, ewNoWait, ErrorCode);
  end; }
