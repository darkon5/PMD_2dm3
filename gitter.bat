@echo off
title supah git scripter
color 03
cls
:repe
echo.
git pull
git add -A
git commit -a -m "auto-upload"
git push
pause
goto repe
pause