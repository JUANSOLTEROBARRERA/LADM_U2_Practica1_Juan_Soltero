package mx.tecnm.tepic.ladm_u2_practica1_juan_soltero

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Lienzo(este:MainActivity) : View(este){
    val este = este
    var circulos = Array<Circulo>(100,{Circulo(this@Lienzo)})
    var circulos2 = Array<Circulo>(200,{Circulo(this@Lienzo)})
    var posX : Float = 0f
    var posY : Float = 0f
    var primeravez = 0
    var cambiar = 0

    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()


        val color = Color.rgb(37, 36, 36)
        c.drawColor(color)

        if(primeravez==0) {
            p.textSize = 130f
            //p.color = Color.BLUE
            ////c.drawRect(320f,1660f,750f,1760f,p)

            //p.color = Color.WHITE
            ////c.drawRect(180f,1000f,900f,1160f,p)
            ////c.drawRect(180f,1400f,900f,1560f,p)

            //p.style = Paint.Style.STROKE
            p.strokeWidth = 10f
            p.color = Color.DKGRAY
            //CASITA------------------------------------------------------------
            c.drawRect(380f, 1000f, 900f, 1260f, p) //BASE CASA
            c.drawRect(0f, 1350f, 1200f, 1700f, p) //CALLE
            p.color = Color.CYAN
            c.drawRect(450f, 1100f, 550f, 1260f, p) //PUERTA
            c.drawRect(620f, 1100f, 820f, 1190f, p) //VENTANA
            c.drawLine(270f, 1000f, 630f, 770f, p) //TECHO
            c.drawLine(270f, 1000f, 470f, 1000f, p) //TECHO
            c.drawLine(630f, 770f, 1000f, 1000f, p) //TECHO
            c.drawLine(1000f, 1000f, 300f, 1000f, p) //TECHO

            p.color = Color.DKGRAY
            c.drawLine(620f, 1145f, 820f, 1145f, p) //VENTANA
            c.drawLine(720f, 1100f, 720f, 1190f, p) //VENTANA
            //------------------------------------------------------------------
            //MONO DE NIEVE-----------------------------------------------------
            p.color = Color.rgb(223, 208, 208)
            c.drawCircle(180f, 1260f, 90f, p)//BASE
            c.drawCircle(180f, 1160f, 70f, p)//BASE
            c.drawCircle(180f, 1060f, 55f, p)//BASE
            p.color = Color.DKGRAY
            c.drawCircle(160f, 1050f, 5f, p)//BASE
            c.drawCircle(210f, 1050f, 5f, p)//BASE
            p.color = Color.rgb(255, 87, 34)
            c.drawLine(190f, 1064f, 240f, 1064f, p)//BASE
            p.color = Color.rgb(0, 0, 0)//NARIZ
            // c.drawLine(190f, 1064f,240f, 1064f,p)//MANO
            c.drawRect(125f, 950f, 225f, 1030f, p)//SOMBRERO
            p.color = Color.WHITE
            c.drawRect(125f, 980f, 225f, 990f, p)//SOMBRERO
            //------------------------------------------------------------------
            //LUNA
            p.color = Color.YELLOW
            c.drawCircle(280f, 300f, 120f, p)
            p.color = Color.rgb(37, 36, 36)
            c.drawCircle(325f, 300f, 90f, p)
        }
        if(cambiar<300) {
            for (num in 0..circulos.size - 1) {
                posX = circulos[num].posX.toFloat()
                posY = circulos[num].posY.toFloat()
                p.color = Color.WHITE
                c.drawCircle(posX, posY, circulos[num].tamanio.toFloat(), p)
            }
        }else{
            for (num in 0..circulos2.size - 1) {
                posX = circulos2[num].posX.toFloat()
                posY = circulos2[num].posY.toFloat()
                p.color = Color.WHITE
                c.drawCircle(posX, posY, circulos2[num].tamanio.toFloat(), p)
            }
        }
        mover()
        invalidate()
    }
    fun mover() = GlobalScope.launch {

        este.runOnUiThread {

            if(cambiar<300) {
                for (num in 0..circulos.size - 1) {
                    if (circulos[num].estado1 == 0) {
                        circulos[num].posY++
                        //circulos[num].posY+=circulos[num].velocidad
                        if(circulos[num].tamanio>10){
                            circulos[num].posY += 2
                        }
                        if(circulos[num].tamanio>=5 && circulos[num].tamanio<10){
                            circulos[num].posY += 1
                        }
                        //circulos[num].posY += circulos[num].vertical
                        if (cambiar == 150) {
                            //circulos = Array<Circulo>(100,{Circulo(this@Lienzo)})
                            cambiar = (0..301).random()
                        }
                        if (cambiar >= 150) {
                            //circulos = Array<Circulo>(100,{Circulo(this@Lienzo)})
                            circulos[num].posY += 10
                        }
                        if (cambiar == 300) {
                            //circulos = Array<Circulo>(200,{Circulo(this@Lienzo)})
                        }
                        if (cambiar >= 300) {
                            //circulos = Array<Circulo>(170,{Circulo(this@Lienzo)})
                            circulos[num].posY += 13
                            circulos[num].posX += 13
                        }
                        if (cambiar == 450) {
                            cambiar = (0..301).random()
                        }
                    } else {
                        circulos[num].posY = 0
                        //circulos[num].posY=circulos[num].posY-circulos[num].velocidad
                        //circulos[num].posY=circulos[num].posY-circulos[num].vertical
                    }
                    if (circulos[num].posY >= 1868) {
                        circulos[num].estado1 = 1
                    }
                    if (circulos[num].posY <= 0) {
                        circulos[num].estado1 = 0
                    }
                    if (circulos[num].posX >= 1080) {
                        circulos[num].posX = 0
                    }
                }
                cambiar++
            }else{
                if(cambiar<300) {
                    for (num in 0..circulos.size - 1) {
                        if (circulos[num].estado1 == 0) {
                            circulos[num].posY++
                            //circulos[num].posY+=circulos[num].velocidad
                            if(circulos2[num].tamanio>10){
                                circulos2[num].posY += 2
                            }
                            if(circulos2[num].tamanio>=5 && circulos2[num].tamanio<10){
                                circulos2[num].posY += 1
                            }
                            //circulos[num].posY += circulos[num].vertical
                            if (cambiar == 150) {
                                //circulos = Array<Circulo>(100,{Circulo(this@Lienzo)})
                                cambiar = (0..301).random()
                            }
                            if (cambiar >= 150) {
                                //circulos = Array<Circulo>(100,{Circulo(this@Lienzo)})
                                circulos[num].posY += 10
                            }
                            if (cambiar == 300) {
                                //circulos = Array<Circulo>(200,{Circulo(this@Lienzo)})
                            }
                            if (cambiar >= 300) {
                                //circulos = Array<Circulo>(170,{Circulo(this@Lienzo)})
                                circulos[num].posY += 13
                                circulos[num].posX += 13
                            }
                            if (cambiar == 450) {
                                cambiar = (0..301).random()
                            }
                        } else {
                            circulos[num].posY = 0
                            //circulos[num].posY=circulos[num].posY-circulos[num].velocidad
                            //circulos[num].posY=circulos[num].posY-circulos[num].vertical
                        }
                        if (circulos[num].posY >= 1868) {
                            circulos[num].estado1 = 1
                        }
                        if (circulos[num].posY <= 0) {
                            circulos[num].estado1 = 0
                        }
                        if (circulos[num].posX >= 1080) {
                            circulos[num].posX = 0
                        }
                    }
                    cambiar++
                }else{
                    for (num in 0..circulos2.size - 1) {
                        if (circulos2[num].estado1 == 0) {
                            circulos2[num].posY++
                            //circulos[num].posY+=circulos[num].velocidad
                            circulos2[num].posY += circulos2[num].vertical
                            if (cambiar == 150) {
                                //circulos = Array<Circulo>(100,{Circulo(this@Lienzo)})
                                cambiar = (0..301).random()
                            }
                            if (cambiar >= 150) {
                                //circulos = Array<Circulo>(100,{Circulo(this@Lienzo)})
                                circulos2[num].posY += 10
                            }
                            if (cambiar == 300) {
                                //circulos = Array<Circulo>(200,{Circulo(this@Lienzo)})
                            }
                            if (cambiar >= 300) {
                                //circulos = Array<Circulo>(170,{Circulo(this@Lienzo)})
                                circulos2[num].posY += 13
                                circulos2[num].posX += 13
                            }
                            if (cambiar == 450) {
                                cambiar = (0..301).random()
                            }
                        } else {
                            circulos2[num].posY = 0
                            //circulos[num].posY=circulos[num].posY-circulos[num].velocidad
                            //circulos[num].posY=circulos[num].posY-circulos[num].vertical
                        }
                        if (circulos2[num].posY >= 1868) {
                            circulos2[num].estado1 = 1
                        }
                        if (circulos2[num].posY <= 0) {
                            circulos2[num].estado1 = 0
                        }
                        if (circulos2[num].posX >= 1080) {
                            circulos2[num].posX = 0
                        }
                    }
                    cambiar++
                }

            }
        }
        delay(3000L)
    }
}
class Circulo(lz: Lienzo){
    var posX = (0..1080).random()
    var posY = (0..1868).random()
    var tamanio = (5..15).random()
    var estado = 0
    var velocidad = (0..3).random()
    var horizontal = (0..2).random()
    var vertical = 0
    var estado1 = 0
    //val colorr = Color.rgb((0..255).random(),(0..255).random(),(0..255).random())
}