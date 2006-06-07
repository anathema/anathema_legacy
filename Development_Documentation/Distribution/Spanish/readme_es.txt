===------------------------------------------------------------------------------===
	    ANATHEMA v0.11 "KIRIGHAST"
===------------------------------------------------------------------------------===

Anathema Leeme
(C) 2004,2005 por Sandra Sieroux y Urs Reupke
"Charmer" Generador-de-Arboles de Encantamientos creado por Daniel Hohenberger
Exalted (C) 2001-2005 by White Wolf Publishing, Inc.
http://anathema.sf.net

Anathema todavía esta en etapa beta.
Por favor vuelve regularmente para que estés actualizado.


===------------------------------------------------------------------------------===
	            TABLA DE CONTENIDO
===------------------------------------------------------------------------------===

	I - Acerca de Anathema
	II - Bajar e Instalación
	III - Lanzando
	IV - Características
	V - Customización
	VI - Errores de software
	VII - Contacto
	VIII - Contribuye
	IX - Gracias!
	X - Licencia


===------------------------------------------------------------------------------===
		I - Acerca de Anathema
===------------------------------------------------------------------------------===

Anathema es un conjunto exhaustivo de todos los aspectos del manejo de series de 
Exalted, ofreciendo manejo del personaje y campaña, biblioteca de medios, notas y mas.

Esa es la intención que tenemos. 
Este lanzamiento es apenas uno mas de muchos pasos en un camino largo. 
Disfrutalo.


===------------------------------------------------------------------------------===
		III - Bajar e Instalación
===------------------------------------------------------------------------------===

Anathema esta disponible gratis en http://sourceforge.net/projects/anathema

El programa viene en dos sabores:

-Anathema_user.zip 
	a.k.a Anathema-en-una-Caja
	Contiene todas las bibliotecas y los proyectos binarios.
	Extraerlo a una carpeta nueva (manteniendo la ruta) y estas listo.
	Si es la primera vez que lo usas, este es el paquete para ti.

-Anathema_developer.zip 
	Contiene todas las bibliotecas y los proyectos binarios, así como el código 
	fuente mas reciente.
	Extraerlo a una carpeta nueva (manteniendo la ruta) y tu encontraras Anathema 
	listo con todas las fuentes listas para inspección y cambios.
	Si es la primera vez que lo usas y estas interesado en el "¿Como?" así como en el
	"¿Que?", anda y bajalo.

En el primer lanzamiento, usuarios de Linux necesitan darle permisos de leer y escribir al principal
directorio de Anathema o especifica un directorio deposito. (Vea la "Sección III - Lanzando")

Una guia cubriendo bajar, instalacion y lanzamiento esta disponible en la pagina del proyecto.

===------------------------------------------------------------------------------===
		III - Lanzando
===------------------------------------------------------------------------------===

Corre el programa al escribir "java -jar anathema.jar".
Usuarios de Windows pueden intentar corriéndolo como "javaw -jar anathema.jar"

Usuarios de Linux tienen que asegurarse de que los permisos de leer y escribir están concedidos al directorio de Anathema o
que el apropiado esta especificado al arranque, usando el parámetro '-Drepository', ejemplo:
  java -Drepository="C:\AnathemaRepository" -jar anathema.jar
para guardar archivos en la carpeta AnathemaRepository en el disco C.

Anathema necesita por lo menos Java Runtime Environment 5.0 para correr.
Para determinar tu versión escribe "java -version". 
Puedes siempre tener la versión mas actualizada en "http//java.sun.com".

Una guia cubriendo bajar, instalacion y lanzamiento esta disponible en la pagina del proyecto.

===------------------------------------------------------------------------------===
		IV - Características
===------------------------------------------------------------------------------===

Después de toda esta charla, seguramente te preguntas: "¿Que es lo que Anathema hace para mi?"
Aquí va:

+ Genera y mantiene los 5 tipos de Exaltados base
+ Genera descripciones de personajes
+ Imprime hojas de personaje y descripciones a PDF
+ Organiza la trama de tus series
+ Maneja una base de datos de musica para tus series
+ Ve muchos datos de los Encantamientos de Exaltados

ve el archivo versions.txt para la historia completa de las versiones.

Si te falta una característica, sientase libre de reportarla usando el "RFE" tracker en el
sitio del proyecto de SourceForge o al contactarnos directamente.


===------------------------------------------------------------------------------===
		V - Customizacion
===------------------------------------------------------------------------------===

Para configurar Anathema, visita el dialogo de "Preferencias" en el menu "Extras".
El dialogo te permite cambiar el lenguaje del programa de Ingles a Español.
(Contactanos si quieres ayudar a que Anathema este en tu lenguaje!)

Aparte de esos ajustes de programa, el usuario podrá customizar la lista de las naturalezas y las condiciones
para ganar voluntad. Ve las instrucciones en el archivo /data/nature_instructions_es.txt para los detalles.

Todos los ajusten necesitan que Anathema sea recomenzado para tomar efecto.

La extencion completa de estas opciones esta descrita en la guia de instalacion de Anathema disponible en 
la pagina del proyecto.

===------------------------------------------------------------------------------===
		VI - Errores de software
===------------------------------------------------------------------------------===

A pesar de nuestros esfuerzos, lo mas probable es que
Anathema no esta libre de errores de software. Si encuentras
algún problema, favor de notificarnos 
(vea la Sección V - Contacto para los detalles) o reportarlo por medio
del sitio del proyecto de Sourceforge, usando el "Bugs" tracker. 
Gracias por ayudarnos a mejorar!

Problemas Conocidos:
---------------------

Las Reglas de Combo para Lunares ilegalmente permite Encantamientos de Artes Marciales sean puestos en un 
combo con Encantamientos basados en Atributos.

A los Siderales todavia le falta el trasfondo de Conecciones.


La pestaña de "Meritos y Defectos" cambia de tamaño cuando los articulos son seleccionados.

Un problema a sido reportado cunado re-cargas personajes despues de cerrarlos,
pero es muy elusivo. Favor de contactarnos si encuentras este error de software.

Vea el archivo versions.txt para la historia completa de los errores de software arreglados.

===------------------------------------------------------------------------------===
		VII - Contacto
===------------------------------------------------------------------------------===

Anathema es desarrollado por Sandra Sieroux y Urs Reupke.
Contactanos en 
urskr@users.sourceforge.net
vestrial@users.sourceforge.net 
Con cualquier preguntas, comentarios o sugerencias.

Para reportar errores de software y peticiones de alguna característica, también puedes usar 
el Tracking System de SourceForge.net en
http://sourceforge.net/projects/anathema

También puedes localizarnos mediante un Mensaje Privado en los forums de rpg.net - 
solo envía tu mensaje al usuario "UKR".

Visita el sitio web de Anathema en http://anathema.sf.net


===------------------------------------------------------------------------------===
		VIII - Contribuye
===------------------------------------------------------------------------------===

Anathema es código abierto, por lo tanto, tienes acceso libre al
código fuente. Si piensas que hay algo extraño o tienes una gran idea de alguna
característica o alguna otra mejora solo implementala y envianosla para que la 
examinarla e integrarla.

Si estas interesado en ver Anathema en tu lengua madre,
favor de contactarnos - estamos interesados en traductores de todo el mundo 
(y es realmente sencillo).

Otra manera de contribuir es creando una base de datos para Encantamientos y Conjuros
 - la manera mas fácil de fama eterna.

Al menos en este punto no aceptamos otras personas en el equipo del desarrollo. 
Las razones por esto son múltiples, si estas interesado en ellas, sientate libre de enviarnos 
un correo electrónico.
Actualmente, estamos considerando como permitir que plugins sean escritos, para que puedas
mas fácilmente participar.


===------------------------------------------------------------------------------===
		IX - Gracias
===------------------------------------------------------------------------------===

Gracias a todas las personas que nos mostraron todos aquellos grandes y pequeños errores de software.

Alabanzas a Xanatos por el agradable icono.
Gracias a uteck por enviar el bosquejo para la Guia de Usuario y por su esposa por hacer pruebas beta.

Philip "The_Burrito" Markus nos proporciono mucha informacion de los hechizos de Necromancia y Encantamientos Lunares.
Patches hizo lo mismo con los Reyes Dragon.
Gracias a Weltenreiter y Bluerps quienes nos ayudaron hace tiempo a organizar la informacion.

Extendemos un gran "Gracias" al Sr. Ricardo Rodriguez y Ms. Rafaela Lombardo,
quienes traducieron el programa y el sitio de web, respectivamente, al español.
Ricardo tambien merece ser mencionado por encontrar inumerables errores y errores tipograficos
en el codigo y la base de datos. Como dije antes: You rock!

En http://hd42.de pueden visitar al Sr. Daniel Hohenberger, quien primero escribo el software para
generar imagenes de las cascadas de encanatmientos. Gracias, nos ahorrastes mucho trabajo e inspirastes a
crear un sistema completamente automatizado.

Si estas interesado en la versión avanzada de las hojas de personaje, favor de
visitar a voidstate en http://www.voidstate.com. Alabanzas a el, también.


===------------------------------------------------------------------------------===
		X - Licencia
===------------------------------------------------------------------------------===

Anathema es publicado bajo el GNU General Public License (GPL).
Puedes usarlo y distribuirlo libremente.
Favor nota que Anathema hace el uso extenso de software de terceros que puede ser
gobernado por otras licencias.
Vea el archivo license.txt para detalles.


===------------------------------------------------------------------------------===
		Fin del Leeme.
===------------------------------------------------------------------------------===