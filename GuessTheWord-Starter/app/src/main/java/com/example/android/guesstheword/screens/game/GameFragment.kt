/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel


    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // below ive added  to instantiate view model with view model providers

        Log.i("GameFragment", "Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)


        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        // Set the viewmodel for databinding - this allows the bound layout access
// to all the data in the ViewModel
        binding.gameViewModel = viewModel

        // Specify the fragment view as the lifecycle owner of the binding.
// This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner


//        /** Setting up LiveData observation relationship **/
//        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
//            binding.scoreText.text = newScore.toString()
//        })
//
//        /** Setting up LiveData observation relationship **/
//        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
//            binding.wordText.text = newWord
//        })

        viewModel.isgameFinsihed.observe(viewLifecycleOwner, Observer { gameFinsihedChange ->
            if (gameFinsihedChange) {
                gameFinished()
            }
        })

        // removed below because now ive made onlcick on xml layout itself

//        binding.correctButton.setOnClickListener { onCorrect() }
//        binding.skipButton.setOnClickListener { onSkip() }
//        binding.endGameButton.setOnClickListener { onEndGame() }
//        updateScoreText()
//        updateWordText()
        return binding.root

    }
//
//    private fun onEndGame() {
//        gameFinished()
//    }

    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
        NavHostFragment.findNavController(this).navigate(action)
    }


    /** Methods for buttons presses **/

//    private fun onSkip() {
//        viewModel.onSkip()
////        updateWordText()
////        updateScoreText()
//
//    }
//
//    private fun onCorrect() {
//        viewModel.onCorrect()
////        updateWordText()
////        updateScoreText()
//    }


    /** Methods for updating the UI **/
    // these below methods are not required as we added an observer, so im keeping it

//    private fun updateWordText() {
//        binding.wordText.text = viewModel.word.value
//    }
//
//    private fun updateScoreText() {
//        binding.scoreText.text = viewModel.score.value.toString()
//    }
}
