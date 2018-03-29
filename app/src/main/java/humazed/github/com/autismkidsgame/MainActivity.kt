package humazed.github.com.autismkidsgame

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import humazed.github.com.autismkidsgame.diff.DifferenceActivity
import humazed.github.com.autismkidsgame.draw.DrawActivity
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

        fitTheShapeImageView.setOnClickListener {
            MaterialDialog.Builder(this)
                    .title(R.string.level_difficulty)
                    .items("Easy", "Medium", "Hard")
                    .itemsCallbackSingleChoice(0) { dialog, view, which, text ->
                        startActivity<MatchingActivity>()
                        true
                    }
                    .positiveText(R.string.action_ok)
                    .negativeText(R.string.action_cancel)
                    .show()
        }

        findTheDiffImageView.setOnClickListener {
            startActivity<DifferenceActivity>()
        }

        drawImageView.setOnClickListener {
            startActivity<DrawActivity>()
        }

    }
}
