# Ruta del archivo de salida
$outputFile = "combined_files.txt"

# Si el archivo de salida existe, elimínalo
if (Test-Path $outputFile) {
    Remove-Item $outputFile
}

# Crea (o inicia) el archivo de salida con un saludo amistoso en UTF-8
Set-Content -Path $outputFile -Value "¡Hola! Voy a entregarte varios archivos con el código de mi aplicación web.`r`n" -Encoding UTF8
Add-Content -Path $outputFile -Value "`r`n" -Encoding UTF8

# Encuentra todos los directorios en el directorio actual (nivel 1)
$directories = Get-ChildItem -Directory

# Listas para almacenar las rutas de los archivos entregados y sin código
$copiedFiles = New-Object System.Collections.ArrayList
$emptyFiles  = New-Object System.Collections.ArrayList

# Procesa cada directorio
foreach ($dir in $directories) {
    # Pregunta al usuario si desea copiar los archivos del directorio
    $response = Read-Host -Prompt "¿Desea copiar los archivos con extensión .kt del directorio $($dir.Name)? (sí/no)"

    # Convierte la respuesta a minúsculas
    $response = $response.ToLower()

    if ($response -eq "sí" -or $response -eq "si") {
        Add-Content -Path $outputFile -Value "Código para los archivos .kt en el directorio $($dir.Name) y sus subdirectorios:" -Encoding UTF8

        # Encuentra todos los archivos .kt en el directorio actual y sus subdirectorios
        $files = Get-ChildItem -Path $dir.FullName -Recurse -File -Include *.kt

        foreach ($file in $files) {
            Add-Content -Path $outputFile -Value "---------------------------" -Encoding UTF8
            Add-Content -Path $outputFile -Value "* Código para $($file.FullName):" -Encoding UTF8

            # Verifica si el archivo está vacío
            if ($file.Length -eq 0) {
                Add-Content -Path $outputFile -Value "El archivo está vacío porque no tiene código" -Encoding UTF8
                # Agrega la ruta relativa del archivo a la lista de archivos sin código
                [void]$emptyFiles.Add((Resolve-Path $file.FullName -Relative))
            }
            else {
                # Lee todo el archivo como texto en crudo (-Raw) para evitar división por líneas
                $fileContent = Get-Content -Path $file.FullName -Raw

                # Escribe el contenido al archivo de salida, forzando la codificación a UTF-8
                $fileContent | Out-File -FilePath $outputFile -Append -Encoding UTF8

                # Agrega la ruta relativa del archivo a la lista de archivos entregados
                [void]$copiedFiles.Add((Resolve-Path $file.FullName -Relative))
            }
            Add-Content -Path $outputFile -Value "`r`n`r`n" -Encoding UTF8
        }
    }
    else {
        Write-Host "Saltando el directorio $($dir.Name) y todos sus subdirectorios..."
    }
}

# Sección ejecutada después de procesar todos los directorios

# Imprime la cantidad y las rutas relativas de todos los archivos entregados con código al final
$numFiles = $copiedFiles.Count
Add-Content -Path $outputFile -Value "Se entregaron $numFiles archivos con código." -Encoding UTF8
if ($numFiles -gt 0) {
    Add-Content -Path $outputFile -Value "Las rutas de los archivos entregados son:" -Encoding UTF8
    foreach ($file in $copiedFiles) {
        Add-Content -Path $outputFile -Value $file -Encoding UTF8
    }
}
else {
    Add-Content -Path $outputFile -Value "No se entregaron archivos con código." -Encoding UTF8
}

# Imprime la cantidad y las rutas relativas de todos los archivos sin código al final
$numEmptyFiles = $emptyFiles.Count
Add-Content -Path $outputFile -Value "`r`nSe encontraron $numEmptyFiles archivos sin código." -Encoding UTF8
if ($numEmptyFiles -gt 0) {
    Add-Content -Path $outputFile -Value "Las rutas de los archivos sin código son:" -Encoding UTF8
    foreach ($file in $emptyFiles) {
        Add-Content -Path $outputFile -Value $file -Encoding UTF8
    }
}
else {
    Add-Content -Path $outputFile -Value "No se encontraron archivos sin código." -Encoding UTF8
}
