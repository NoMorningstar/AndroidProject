package com.itri.snake

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var snakeViewModel: SankeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        snakeViewModel = ViewModelProvider(this).get(SankeViewModel::class.java)
        snakeViewModel.body.observe(this, Observer {
            game_view.snakeBody = it
            game_view.invalidate()
        })

        snakeViewModel.apple.observe(this, Observer {
            game_view.apple = it
            game_view.invalidate()
        })

        snakeViewModel.score.observe(this, Observer {
            score.text = it.toString()
        })
        snakeViewModel.gameState.observe(this, Observer {
            if (it == GameState.GAME_OVER) {
                AlertDialog.Builder(this)
                        .setTitle("GAME")
                        .setMessage("Game Over")
                        .setNeutralButton("OK", null)
                        .setPositiveButton("replay", DialogInterface.OnClickListener { dialog, which ->
                            snakeViewModel.reset()
                        })
                        .show()
            }
        })
        snakeViewModel.start()
        top.setOnClickListener { snakeViewModel.move(Direction.TOP) }
        down.setOnClickListener { snakeViewModel.move(Direction.DOWM) }
        right.setOnClickListener { snakeViewModel.move(Direction.RIGHT) }
        left.setOnClickListener { snakeViewModel.move(Direction.LEFT) }
    }
}