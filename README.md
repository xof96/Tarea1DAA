# Tarea1 DAA

<hr>

##Packages

###p1:

* Contiene las carpetas nodo, create, test.

* ###nodo:
    * __Nodo__: Clase abstracta que contiene diccionario (_Hashtable_) como atributo.
    * __Producto__: Hereda de Nodo y su Hastable contiene los atributos que se mencionan en el enunciado.
    * __Consumidor__: Hereda de Nodo y su Hastable contiene los atributos que se mencionan en el enunciado.
    * __Database__: Clase encargada de abrir un archivo e insertar nodos en dicho archivo, también puede ordenar nodos en un archivo.
* ###create:
    * Contiene los ejecutables para insertar nodos en archivos.
* ###test:
    * __FileTester__: Clase en cargada de crear archivo e insertar nodos según ciertos parámetros.
    * __MergeTester__: SPIKE...


###p2:

* Contiene las carpeta btree, create, files y test.
* ###btree:
    * __BTree__: Es la clase en cargada de crear un árbol B
    * __BInner__: Corresponde a los nodos internos del árbol.
    * __BLeaf__: Corresponde a las hojas del árbol.
    * __BNode__: Interfaz para cualquier tipo de nodo del árbol.
    * __SplitResponse__: Es una clase que tiene los parámetros que le tiene que pasar un nodo a su padre cuando hace 
    split. Pues, esta repuesta es fundamental ya que un nodo no tiene una referencia a su padre.
* ###create:
    * Contiene los archivos ejecutables encargados de correr los test para _1e1, 1e2, 1e3, 1e4, 1e5, 1e6 y 1e7_. Estos
    archivos crean un árbol con un path inicial, correspondiente al path del archivo que es la raíz del árbol en un 
    comienzo. Luego se insertan la cantidad correspondiente de elementos aleatorios.
* ###files:
    * Contiene los archivos correspondientes a los nodos de los árboles.
* ###test:
    * __BTreeTester__: Es la clase que se encarga de insertar los nodos al árbol.
    

###p3:


* __BTreeForQueriesMaker__: Es la clase encargada de crear los árboles para posteriormente hacer las consultas de la
pregunta 3.
* __QueryMaker__: Ejecuta las consultas 1 y 2 de la pregunta 3.

<br>
<br>

##Para Ejecutar la Tarea.

Para ejecutar las preguntas de esta tarea, las carpetas create de p1 y p2 contienen los ejecutables. Es **IMPORTANTE** 
la p2 tener la carpeta _files_ dentro de p2, aunque está vacía. Para la p3 también es **IMPORTANTE** tener las carpetas
_files_ y _res_, también pueden estar vacías.

    
