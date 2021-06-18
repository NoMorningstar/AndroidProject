package com.itri.snake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.fixedRateTimer
import kotlin.random.Random

class SankeViewModel : ViewModel(){
    //储存的是一个位置，更新我的那个红点
    private lateinit var applePosition: Position
    //蛇身上的身体的资料，它是一个list集合
    var body = MutableLiveData<List<Position>>()
    //那个贪吃蛇在追的那一个苹果啊，然后他预设是
    var apple = MutableLiveData<Position>()
    //分数是零
    var score = MutableLiveData<Int>()
    //游戏状态,因为我这个在撞墙了之后啊，我想要让玩家可以得知游戏结束了。
    var gameState = MutableLiveData<GameState>()
    //蛇吃苹果后的长度，它是一个list集合
    private var snakeBody = mutableListOf<Position>()
    //那接下来就要设计方向，移动的方向，可是方向有只有四种方向,那预设哦就是往左边跑啊
    private var direction = Direction.LEFT
    private var point : Int = 0


    fun start(){
        score.postValue(point)
        snakeBody.apply{
            add(Position(10, 10))
            add(Position(11, 10))
            add(Position(12, 10))
            add(Position(13, 10))
        }
        body.value = snakeBody
        generateApple()
        fixedRateTimer("timer", true, 500, 400){
            var snakeHead = snakeBody!!.first().copy().apply {
                when(direction){
                    Direction.LEFT -> x--
                    Direction.RIGHT -> x++
                    Direction.DOWM -> y++
                    Direction.TOP -> y--
                }
                if ( snakeBody.contains(this) || x < 0 || x >= 20 || y <  0 || y >=  20 ){
                    cancel()
                    gameState.postValue(GameState.GAME_OVER)
                }
            }
            snakeBody.add(0, snakeHead)
            if (snakeHead != applePosition){
                snakeBody.removeLast()
            }else{
                point+=100
                score.postValue(point)
                generateApple()
            }
            body.postValue(snakeBody)
        }
    }

    fun reset(){
        snakeBody.clear()
        start()
    }

    fun generateApple(){
        do {
            applePosition = Position(Random.nextInt(20), Random.nextInt(20))
        }while (snakeBody.contains(applePosition))
        apple.postValue(applePosition)
    }

    fun move(dir : Direction){
        direction = dir
    }


}
//这个时候需要一个储存x y的资料，所以我定义一个data class 叫里面有两个，一个是x 一个是y。
//再把它使用在集合里面的范式
data class Position(var x : Int, var y : Int)
//然后direction 里面只有四个，就是上下还有左跟右。
enum class Direction{
    RIGHT, LEFT, DOWM, TOP
}
enum class GameState{
    GAME_OVER, GAME_GOING
}