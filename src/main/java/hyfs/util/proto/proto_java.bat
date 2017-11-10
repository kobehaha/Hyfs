@echo off
@echo Start generating files


protoc.exe --java_out=../../../ ./protoFile/*.proto
IF ERRORLEVEL 1 goto exeFail
IF ERRORLEVEL 0 goto exeSuccess

:exeFail
echo Failed

:exeSuccess
echo succeed

pause



