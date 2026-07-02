@ECHO OFF
SETLOCAL

SET BASE_DIR=%~dp0
SET WRAPPER_JAR="%BASE_DIR%.mvn\wrapper\maven-wrapper.jar"
SET WRAPPER_PROPERTIES="%BASE_DIR%.mvn\wrapper\maven-wrapper.properties"
SET WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

IF NOT EXIST %WRAPPER_JAR% (
    ECHO Downloading Maven Wrapper...
    FOR /F "tokens=2 delims==" %%A IN ('FINDSTR wrapperUrl %WRAPPER_PROPERTIES%') DO SET WRAPPER_URL=%%A
    powershell -Command "Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile %WRAPPER_JAR%"
)

IF "%JAVA_HOME%"=="" (
    SET JAVA_EXE=java
) ELSE (
    SET JAVA_EXE=%JAVA_HOME%\bin\java.exe
)

%JAVA_EXE% -classpath %WRAPPER_JAR% "-Dmaven.multiModuleProjectDirectory=%BASE_DIR%" %WRAPPER_LAUNCHER% %*

ENDLOCAL
