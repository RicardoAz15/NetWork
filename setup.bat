@ECHO OFF

start bundle update

echo. 
echo Installing Fastlane
gem install fastlane -NV
echo. 
echo Install plugins
cd Retrofit\retrofit_montepio\
call bundle exec fastlane install_plugins
cd ..\..\
echo. 
echo Replacing files
replace Extra_Files\gradle_helper.rb C:\Ruby26-x64\lib\ruby\gems\2.6.0\gems\fastlane-2.130.0\fastlane\lib\fastlane\helper 
replace Extra_Files\instrumented_tests_action.rb C:\Ruby26-x64\lib\ruby\gems\2.6.0\gems\fastlane-plugin-instrumented_tests-0.2.0\lib\fastlane\plugin\instrumented_tests\actions

echo Wich project do u want to test:
echo 1:Retrofit
echo 2:Volley
echo 3:Both

set /p input= "Response: "

if "%input%" == "1" (
	echo.
	echo Testing Retrofit
	cd Retrofit\retrofit_montepio\
	call bundle exec fastlane test
	cd ..\..\
)
if "%input%" == "2" (
	echo.
	echo Testing Retrofit
	cd Volley\Volley_Montepio\
	call bundle exec fastlane test
	cd ..\..\
)
if "%input%" == "3" (
	echo.
	echo Testing Both:
	echo.
	echo Testing Retrofit
	cd Retrofit\retrofit_montepio\
	call bundle exec fastlane test
	cd ..\..\
	echo.
	PAUSE
	echo.
	echo Testing Retrofit
	cd Volley\Volley_Montepio\
	call bundle exec fastlane test
	cd ..\..\
)

start iexplorer.exe https://drive.google.com/drive/folders/1w6Q1lr42Bx3-AMxU5pNd_MkBACMOFtUv?usp=sharing
start iexplorer.exe https://ricardo-set3240.slack.com/messages/CMS8RA4BY

PAUSE