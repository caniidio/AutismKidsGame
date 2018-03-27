package humazed.github.com.autismkidsgame

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import humazed.github.com.autismkidsgame.puzzle.ui.ChoosePicActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        puzzleButton.setOnClickListener {
            ChoosePicActivity.actionStart(this)
        }

    }
}
