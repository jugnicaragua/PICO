# PICO
Un Sistema Contable básico para MicroEmpresas Nicaragüenses. Didáctico y Colaborativo.

### >> ¡Importante!, Aun no tenemos release, para el desarrollo estamos usando la rama develop

[![Build Status](https://travis-ci.org/jug-ni/PICO.svg?branch=master)](https://travis-ci.org/jug-ni/PICO)

# Objetivo
* Ser Didáctico para los que pretenden aprender Java utilizando las mejores prácticas.
* Ser una solución para MicroEmpresas que necesitan llevar contabilidad.
* Ser un reductor de la cantidad de proyectos de graduación en centros técnicos y universidades enfocados en Contabilidad.

# Características Funcionales
* Didáctico
  - Totalmente comentado.
* Sencillo
* Intuitivo
* Portable
* mono empresa
* Reportes contables

# Características Técnicas.
* De escritorio
* Portable
* Base de datos Empotrada
* Reportes integrados
* Ejecutable desde un Flash Drive
* Windows, Linux, BSD and MAC
* Totalmente comentado y Documentado


# Stack
```
  STACK : Conjunto de Tecnologías utilizadas
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
Para el estilo de codificación, usaremos el estilo creado por Google:
[](https://google.github.io/styleguide/javaguide.html)


### Donde descargar los archivos para los IDE.
https://github.com/google/styleguide


#### Eclipse:
https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml

#### IntelliJIDEa
https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml

#### NetBeans
--> notiene, por ahora.


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

