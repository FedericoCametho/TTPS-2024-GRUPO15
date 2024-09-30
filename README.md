# Grupo 15 - TTPS 2024

![guia.png](../buffet/guia.png)


**DEVELOP**: Se van agregando las funcionalidades aca, no se trabaja directo sobre el branch, se crea un branch nuevo por cada funcionalidad (puntos azules), cuando el desarrollador terminó la funcionalidad se hace un *MERGE REQUEST (preferentemente con squash)* hacia el branch **DEVELOP**, se hace una revision del codigo entre los programadores viendo que se hizo y si está bien, si se acepta pasa de version.

**RELEASE**: Cuando todas las funcionalidades estan agregadas correctamente en **DEVELOP** se transfiere todo a este branch, signfica que está en preproduccion, no se hacen mas agregados de funcionalidades y generalmente se testea todo el sistema, una vez comprobado se hace un merge al main, se actualizan las versiones.


**MAIN**: Contiene las ultimas funcionalidades del proyecto, es el ultimo paso a actualizar. El codigo que esta en produccion.




# Guia:

Descargan el proyecto, con la consola se colocan en el directorio y ponen
```
 git checkout develop
```
Con ese comando se van a parar en el branch develop que va a tener las ultimas funcionalidades

Para crear una funcionalidad tienen que crear un branch para ustedes con el nombre que quieran, parados en la rama develop ponen
```
 git checkout -b funcionalidad1
```
Una vez que hayan terminado la funcionalidad, usando el add y hechos los commit, antes de realizar el merge de la rama, hacer un pull en develop(sirve para actualizar su rama a las nuevas funcionalidades que pudieron agregar otras personas mientras ustedes desarrollaban la suya), para hacer simple pongan
```
 git checkout develop
```
```
git pull origin develop
```
Una vez actualizado develop, vuelven a su rama de funcionalidad y hacen el merge de develop a su rama para traer potenciales cambios que hayan habido durante el desarrollo de la funcionalidad.
```
 git checkout funcionalidad1
```
```
git merge develop
```
Esto va a fusionar la rama de develop con los ultimos cambios a la rama funcionalidad1. Entonces queda pushear los cambios de la rama funcionalidad1 al repositorio remoto.
```
 git push origin funcionalidad1
```
Luego de hacer estos pasos, el merge a la rama develop realizarlo desde github. Crear un PULL REQUEST desde la rama funcionalidad1 a develop y pedirle a un compañero  que lo acepte. 

Una vez realizado esto, desde la consola de comandos del ide, pararse en la rama develop y traerse los cambios fusionados remoto.
```
 git checkout develop
```
```
 git pull origin develop
```










