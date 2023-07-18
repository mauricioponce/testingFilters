package com.eme.filters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eme.filters.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val numbersList = listOf("one", "two", "three", "four")
    private val instanceList = listOf(null, 1, "two", 3.0, "four")

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {

        // Filtro por predicado
        binding.btFilterByPredicate.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.filter { it.length > 3 },
                    "list.filter { it.length > 3 }\nDevuelve la colección de elementos que cumplen la condición. Retorna los elementos con mas de 3 caracteres de largo"
            )
        }


        // Filtro por índice
        binding.btFilterIndexed.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.filterIndexed { index, s -> (index != 0) && (s.length < 5) },
                    "list.filterIndexed { index, s -> (index != 0) && (s.length < 5) }\nRecibe como parámetro el índice y el elemento. Luego filtra que no sean en el primer elemento (index != 0) y que tengan mas de 5 caracteres"
            )
        }

        //filterNot()
        binding.btFilterNot.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.filterNot { it.length <= 3 },
                    "list.filterNot { it.length <= 3 }\n Devuelve una lista de elementos para los cuales el predicado es falso"
            )
        }

        //filterInstance()
        binding.btFilterInstance.setOnClickListener {
            showResults(
                    instanceList,
                    instanceList.filterIsInstance<String>(),
                    "list.filterIsInstance<String>()\nRetorna una colección de elementos de un tipo dado"
            )
        }

        //filterNotNull
        binding.btFilterNotNull.setOnClickListener {
            showResults(
                    instanceList,
                    instanceList.filterNotNull(),
                    "list.filterNotNull<String>()\nRetorna con todos los elementos no nulos"
            )
        }

        /************************************************************************
         ********************** Probando predicados *****************************
         ************************************************************************/
        // Any()
        binding.btAny.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.any { it.endsWith("e") },
                    "list.any { it.endsWith(\"e\") }\nRetorna true si al menos uno de los elementos coincide con el predicado"
            )
        }

        // None
        binding.btNone.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.none { it.endsWith("a") },
                    "list.none { it.endsWith(\"a\") }\nRetorna true si ninguno de los elementos coincide con el predicado"
            )
        }

        // All
        binding.btAll.setOnClickListener {
            showResults(
                    numbersList,
                    numbersList.all { it.endsWith("e") },
                    "list.all { it.endsWith(\"e\") }\nRetorna true si todos los elementos coinciden con el predicado"
            )
        }
    }

    private fun showResults(inputList: List<Any?>, listResult: Any, message: String) {
        binding.inputList.text = "Entrada $inputList"
        binding.tvResultList.text = "Resultado: $listResult"
        binding.tvInputMessage.text = message
    }

}
