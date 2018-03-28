package humazed.github.com.autismkidsgame

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import humazed.github.com.autismkidsgame.matching.MatchingActivity
import humazed.github.com.autismkidsgame.puzzle.ui.ChoosePicActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        puzzleImageView.setOnClickListener {
            ChoosePicActivity.actionStart(this)
        }


        findTheDiffImageView.setOnClickListener {
            startActivity<MatchingActivity>()
        }

        drawImageView.setOnClickListener {

        }

    }
}
