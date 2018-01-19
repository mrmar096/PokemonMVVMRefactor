package net.azarquiel.pokemonappreal.infraestructure.utils

import android.widget.RadioButton
import android.widget.RadioGroup

/**
 * Created by mrmar on 14/1/18.
 */

fun RadioGroup.getOptionSelected():Int =
    (0..this.childCount).first { (getChildAt(it) as RadioButton).isChecked }
