package Rational

/**
 * Created by Hirofumi on 2017/02/24.
 */

/**
 * 有理数クラスの定義
 * Scalaの本からアイディアを借りてきているらしい
 * 自己定義してみて、他ので既存のデータ型と共有できるようにしている
 * */
class Rational(val n: Int, val d: Int){

    // initializer
    init {
        require(d != 0, {"denominator must not be null"})
    }

    // lazy の意味がわかっていない
    private val g by lazy { gcd(Math.abs(n), Math.abs(d)) }
    // もともとは
//    private val g = gcd(Math.abs(n), Math.abs(d))

    val denominator : Int = d / g
    val numerator : Int = n / g

    // 加算メソッド(operator修飾子によって + を使ってもplusメソッドが呼べる)
    // Rationalオブジェクトとの足し算
    operator fun plus(that: Rational):Rational =
        Rational(numerator * that.denominator + that.numerator * denominator, denominator * that.denominator)

    // 加算メソッド(operator修飾子によって + を使ってもplusメソッドが呼べる)
    // Intオブジェクトとの足し算
    operator fun plus(n: Int):Rational =
            Rational(numerator + n * denominator, denominator)

    // override toString() like as Java
    override fun toString(): String = "$numerator/$denominator"

    // 末尾再帰(tailrec)の最適化をする関数
    // 別に tailrec は無くても再帰関数となる
    tailrec private fun gcd(a : Int, b : Int) : Int =
            if(b == 0) a
            else gcd(b, a % b)

}

// 拡張関数という機能を利用して既存クラスのIntオブジェクトをオーバーロード(正確にはオーバーロードではなく、拡張関数という枠なのかも)している。
operator fun Int.plus(r: Rational): Rational = r + this

fun main(args: Array<String>) {
    val half = Rational(1, 2)
    println(half)
//    val zero = Rational(5, 0);
//    print(zero)
    val someNum = Rational(21, 51)
    println(someNum)

    println(half.plus(someNum))
    // + 演算子を使ったplusメソッドの利用
    println(half + someNum)

    // RationalオブジェクトにIntオブジェクトのオーバーロード
    println(Rational(2, 3) + 2)

    // 拡張関数による恩恵
    println(10 + Rational(5, 4))
}
