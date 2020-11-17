package pub.moi.wendao.utils

import java.util.*

class Utils {
    companion object{
        fun getOnlyNumber(function: (Long) -> Boolean): Long {
            val num = getNumber(7)
            return if (function.invoke(num))
                num else getOnlyNumber(function)
        }

        private fun getNumber(i: Int): Long {
            return when (i) {
                7 -> getNumberByRange(100000, 999999)
                8 -> getNumberByRange(1000000, 9999999)
                9 -> getNumberByRange(10000000, 99999999)
                else -> 1782782638
            }

        }
        private fun getNumberByRange(min: Int, max: Int): Long {
            return min + (Random().nextDouble() * (max - min)).toLong()
        }
    }
}