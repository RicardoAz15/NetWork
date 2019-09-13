@ECHO OFF

echo É necessário instalar/fazer update do Fastlane deseja executar instalação/update
set /p input= "[y:Intalação u:Update n:Não é necessário] "

if /i "%input%" == "y" (
	echo Installing Fastlane
	call gem install fastlane -NV
	pause
	echo.
)

if /i "%input%" == "u" (
	echo Updating
	call fastlane update_fastlane
	pause
)

echo.
echo Install plugins
call gem install fastlane-plugin-instrumented_tests
call gem install fastlane-plugin-increment_version_code
call gem install fastlane-plugin-google_drive
pause

echo.
call fastlane install_plugins
pause

echo.
echo Replacing files
replace Extra_Files\gradle_helper.rb C:\Ruby26-x64\lib\ruby\gems\2.6.0\gems\fastlane-2.131.0\fastlane\lib\fastlane\helper
replace Extra_Files\instrumented_tests_action.rb C:\Ruby26-x64\lib\ruby\gems\2.6.0\gems\fastlane-plugin-instrumented_tests-0.2.0\lib\fastlane\plugin\instrumented_tests\actions

for /f "tokens=2-7 delims=\\" %%I in ("%ANDROID_SDK_ROOT%") do set ANDROID_SDK_ROOT=/%%I/%%J/%%K/%%L/%%M/%%N

echo %ANDROID_SDK_ROOT%

echo.
echo Wich project do u want to test:
echo 1:Retrofit
echo 2:Volley
echo 3:Both

set /p input= "Response: "

echo.

set windowBoll="true"
echo Window on emulador?
set /p window= "[y/n] "

if /i "%window%" == "y" set windowBoll="false"

if "%input%" == "1" (
	echo.
	echo Testing Retrofit
	cd Retrofit\retrofit_montepio\
	call fastlane test window:%windowBoll% sdk:%ANDROID_SDK_ROOT% 
	cd ..\..\
)
if "%input%" == "2" (
	echo.
	echo Testing Volley
	cd Volley\Volley_Montepio\
	call fastlane test window:%windowBoll% sdk:%ANDROID_SDK_ROOT%
	cd ..\..\
)
if "%input%" == "3" (
	echo.
	echo Testing Both:
	echo.
	echo Testing Retrofit
	cd Retrofit\retrofit_montepio\
	call fastlane test window:%windowBoll% sdk:%ANDROID_SDK_ROOT%
	cd ..\..\
	echo.
	PAUSE
	echo.
	echo Testing Retrofit
	cd Volley\Volley_Montepio\
	call fastlane test window:%windowBoll% sdk:%ANDROID_SDK_ROOT%
	cd ..\..\
)

PAUSE

start "link" https://drive.google.com/drive/folders/1w6Q1lr42Bx3-AMxU5pNd_MkBACMOFtUv?usp=sharing && start "link" https://ricardo-set3240.slack.com/messages/CMS8RA4BY
