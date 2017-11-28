import java.security.PublicKey;

public class ArbolAVL {
    private NodoArbolAVL raiz;

    public ArbolAVL() {

        raiz = null;
    }
    public NodoArbolAVL obtenerRaiz() {
        return raiz;
    }


    public NodoArbolAVL buscar(int d,NodoArbolAVL r){
        if(raiz==null){
            return null;
        }else if (r.dato==d){
            return r;
        }else if (r.dato<d){
            return buscar(d,r.hijoDerecho);
        }else{
            return buscar(d,r.hijoIzquierdo);

        }
    }
    //Obtener el factor de equilibrio
    public int obtenerFE(NodoArbolAVL x){
        if(x==null){
            return -1;
        }else{
            return x.fe;

        }
    }
    //Rotacion Simple izquierda
    public NodoArbolAVL rotacionIzquierda (NodoArbolAVL c){
        NodoArbolAVL auxilar=c.hijoIzquierdo;
        c.hijoIzquierdo=auxilar.hijoDerecho;
        auxilar.hijoDerecho=c;
        c.fe=Math.max(obtenerFE(c.hijoIzquierdo),obtenerFE(c.hijoDerecho))+1;
        return auxilar;

    }
    //Rotacion simple Derecho
    public NodoArbolAVL rotacionDerecha (NodoArbolAVL c){
        NodoArbolAVL auxilar=c.hijoDerecho;
        c.hijoDerecho=auxilar.hijoDerecho;
        auxilar.hijoDerecho=c;
        c.fe=Math.max(obtenerFE(c.hijoIzquierdo),obtenerFE(c.hijoDerecho))+1;
        return auxilar;

    }
    //Rotacion doble ala Derecha
    public NodoArbolAVL rotacionDobleIzquierda(NodoArbolAVL c){
    NodoArbolAVL temporal;
    c.hijoIzquierdo=rotacionDerecha(c.hijoIzquierdo);
    temporal=rotacionIzquierda(c);
    return temporal;

}
    //Rotacion doble ala Izquierda
    public NodoArbolAVL rotacionDobleDerecha(NodoArbolAVL c){
        NodoArbolAVL temporal;
        c.hijoDerecho=rotacionIzquierda(c.hijoDerecho);
        temporal=rotacionDerecha(c);
        return temporal;


    }
    //Metodo para insertar AVL
    public NodoArbolAVL insertarAVL(NodoArbolAVL nuevo,NodoArbolAVL subAr){
        NodoArbolAVL nuevoPadre=subAr;
        if (nuevo.dato<subAr.dato){
            if(subAr.hijoIzquierdo==null){
                subAr.hijoIzquierdo=nuevo;
            }else{
                subAr.hijoIzquierdo=insertarAVL(nuevo, subAr.hijoIzquierdo);
                if( (obtenerFE(subAr.hijoIzquierdo)- obtenerFE(subAr.hijoDerecho)==2)){
                    if(nuevo.dato<subAr.hijoIzquierdo.dato){
                        nuevoPadre=rotacionIzquierda(subAr);
                    }else{
                        nuevoPadre=rotacionDobleIzquierda(subAr);

                    }
                }

            }
        }else if(nuevo.dato>subAr.dato){
            if(subAr.hijoDerecho==null){
                subAr.hijoDerecho=nuevo;
            }else{
                subAr.hijoDerecho=insertarAVL(nuevo,subAr.hijoDerecho);
                if((obtenerFE(subAr.hijoDerecho) - obtenerFE(subAr.hijoIzquierdo)==2))

            if (nuevo.dato > subAr.hijoDerecho.dato) {
            } else {
                nuevoPadre = rotacionDobleDerecha(subAr);
            }
        }
    }else {

            System.out.println("Nodo Duplicado");
        }
        //Actualizando la altura
        if((subAr.hijoIzquierdo==null)&&(subAr.hijoDerecho!=null)) {
            subAr.fe=subAr.hijoDerecho.fe+1;
        }else if ((subAr.hijoDerecho==null) && (subAr.hijoIzquierdo!=null)){
            subAr.fe=subAr.hijoIzquierdo.fe+1;
        }else{
            subAr.fe=Math.max(obtenerFE(subAr.hijoIzquierdo),obtenerFE(subAr.hijoDerecho))+1;
        }
        return nuevoPadre;
    }
    //Metodo para insertar
    public void insertar(int d){
        NodoArbolAVL nuevo=new NodoArbolAVL (d);
        if (raiz==null){
            raiz=nuevo;
        }else {
            raiz=insertarAVL(nuevo,raiz);
        }
    }
    //Recorridos
//Inorden
    public void inOrden(NodoArbolAVL r) {
        if (r != null) {
            inOrden(r.hijoIzquierdo);
            System.out.print(r.dato + ".");
            inOrden(r.hijoDerecho);

        }
    }
//Preorden
    public void preOrden(NodoArbolAVL r){
        if(r!=null){
            System.out.print(r.dato + ",");
            preOrden(r.hijoIzquierdo);
            preOrden(r.hijoDerecho);
        }
    }
    //Postorden
    public void postOrden(NodoArbolAVL r){
        if(r!=null){
            postOrden(r.hijoIzquierdo);
            postOrden(r.hijoDerecho);
            System.out.print(r.dato + ",");
        }
    }
}
