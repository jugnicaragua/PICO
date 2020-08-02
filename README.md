# PICO
Un Sistema Contable básico para MicroEmpresas Nicaragüenses. Didáctico y Colaborativo.
### Status
![GitHub All Releases](https://img.shields.io/github/downloads/jugnicaragua/subtiava/total?label=Descargas&style=plastic)
![GitHub](https://img.shields.io/github/license/jugnicaragua/subtiava)
![GitHub manifest version](https://img.shields.io/github/manifest-json/v/jugnicaragua/subtiava)
[![Build Status](https://travis-ci.org/jug-ni/PICO.svg?branch=master)](https://travis-ci.org/jug-ni/PICO)

### >> ¡Importante!, Aun no tenemos release, para el desarrollo estamos usando la rama develop


# Objetivo
* Ser Didactico para los que pretenden aprender Java utilizando las mejores practicas.
* Ser una solucion para MicroEmpresas que necesitan llevar contabilidad.
* Ser un reductor de la cantidad de proyectos de graduacion en centros tecnicos y universidades enfocados en Contabilidad.

# Caracteristicas Funcionales
* Didactico
  - Totalmente comentado.
* Sencillo
* Intuitivo
* Portable
* mono empresa
* Reportes contables

# Caracteristicas Técnicas.
* De escritorio
* Portable
* Base de datos Empotrada
* Reportes integrados
* Ejecutable desde un Flash Drive
* Windows, Linux, BSD and MAC
* Totalmente comentado y Documentado


# Stack
```
  Conjunto de Tecnologias utilizadas
```
* Base: JVM OpenJDK (AdoptOpenJDK) Version 11 LTS
* Manejador: maven
* Base de Datos: Empotrada SQLITE  y HSQLDB
* JPA (Persistencia)
* ORM: Hibernate
* Swing
* Reportes: JasperReport
* MVC 
* POO
* Multi IDE

## Estilo de Código:
Para el estilo de codificacion, usaremoes el estilo creado por google: 
https://google.github.io/styleguide/javaguide.html


# Preparando  el IDE
## Importando el estilo de Código en IntelliJIDEa
## Importando el estilo de Código en NetBeans
## Importando el estilo de Código en Eclipse


...

# Como probar desde el código usando Maven. 
Con Maven puedes probar la aplicación simplemente descargando el código y ejecutar los comandos de maven para compilarlo, empaquetarla o ejecutarlo. 
- Compilar:
```
mvn compile
```

- empaquetar
```
mvn package 
```

- instalar
```
mvn install
```

- si la ejecucion de las pruebas fallan entonces omitirlas
```
mvn install -DskipTests=true 
```
## Ejecutando desde la terminal, consola, CMD o Power Shell
```shell
  java -jar tarjet/PICO.jar
```

# Diseño
  TDR <enlace a la página de los TDR>
  UML <Enlace a los diagramas de Caso de USO>
  UI <enlace a las interfaces>

# Como colaborar (¡IMPORTANTE!).
1) Todo colaboracion de codígo debe hacerce mediante PULL PULL REQUEST desde la rama/branch "develop"
2) Reporte de errores, bugs, se hacen mediante issue, solicitudes de mejoras tambien. 



# Licencia
Copyright (c) 2018-2020 Grupo de Usuarios Java de Nicaragua.

Pico Accounting is free libre open source software (FLOSS), licensed under the GNU General Public License version 3 (GPLv3), see the COPYING file or a copy at: https://www.gnu.org/licenses/gpl.txt


# Autores y Colaboradores
JUG Nicaragua, Grupo de Usuarios Java de Nicaragua.
https://javanicaragua.org/nuestro-equipo/

