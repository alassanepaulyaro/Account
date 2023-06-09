package com.example.ca_compte.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.ca_compte.adapters.BankAdapter
import com.example.ca_compte.data.model.Account
import com.example.ca_compte.data.model.Bank
import com.example.ca_compte.data.model.BankItem
import com.example.ca_compte.databinding.FragmentAccountBinding
import com.example.ca_compte.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

/**
 * AccountFragment
 */
@AndroidEntryPoint
class AccountFragment : Fragment(), UserClickListener {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val accountViewModel: AccountViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountViewModel.bankData.observe(viewLifecycleOwner) { result ->
            displayViewVisibility(result)
            if (result is Resource.Success) {
                result.data?.let { setUpListOfAdapter(getListBankToDisplay(it.banks)) }
            }
        }
        accountViewModel.getBankData()
    }

    /**
     * Setup recyclerView list of items
     */
    private fun setUpListOfAdapter(items: List<BankItem>) {
        val bankAdapter = BankAdapter(items, this)
        binding.recyclerviewAccount.apply {
            setHasFixedSize(true)
            adapter = bankAdapter
        }
    }

    /**
     * get list bank CA
     */
    private fun getListBankCA(banks: List<Bank>): List<BankItem.BankUi> {
        return banks.filter { it.isCA == 1 }.sortedBy { it.name }
            .map { BankItem.BankUi(it.name, it.accounts, false) }
    }

    /**
     * get list bank other
     */
    private fun getListBankOther(banks: List<Bank>): List<BankItem.BankUi> {
        return banks.filter { it.isCA == 0 }.sortedBy { it.name }
            .map { BankItem.BankUi(it.name, it.accounts, false) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun mClick(item: Account) {
        val action = AccountFragmentDirections.actionAccountFragmentToOperationFragment(item)
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

    /**
     * Get list of bank
     */
    private fun getListBankToDisplay(banks: List<Bank>): List<BankItem> {
        val listItemsBankUi = mutableListOf<BankItem>()
        listItemsBankUi.clear()
        listItemsBankUi.add(BankItem.Title(1, "Credit Agricole"))
        listItemsBankUi.addAll(getListBankCA(banks))
        listItemsBankUi.add(BankItem.Title(2, "Autres Banques"))
        listItemsBankUi.addAll(getListBankOther(banks))
        return listItemsBankUi
    }

    /**
     * function displays the visibility of the view
     */
    private fun <T> displayViewVisibility(resource: Resource<T>) {
        when (resource) {
            is Resource.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.errorView.visibility = View.GONE
                binding.recyclerviewAccount.visibility = View.GONE
            }

            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.errorView.visibility = View.VISIBLE
                binding.errorView.text = resource.message
                binding.recyclerviewAccount.visibility = View.GONE
            }

            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.errorView.visibility = View.GONE
                binding.recyclerviewAccount.visibility = View.VISIBLE
            }
        }
    }
}