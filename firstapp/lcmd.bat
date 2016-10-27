@echo off
color 0a
cls
start
goto fin
:n
title Terminal - %cd%
set /P entra=%username% $
shell %entra%
goto n
pause
:fin