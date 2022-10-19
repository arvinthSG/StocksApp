package com.example.stocksapp

import com.example.stocksapp.Data.Stock
import com.example.stocksapp.PortfolioPage.PortfolioPageContract
import com.example.stocksapp.PortfolioPage.PortfolioPageFragment
import com.example.stocksapp.PortfolioPage.PortfolioPageModel
import com.example.stocksapp.PortfolioPage.PortfolioPagePresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class PortfolioPagePresenterTest {

    lateinit var presenter: PortfolioPagePresenter
    lateinit var view: PortfolioPageContract.View
    lateinit var model: PortfolioPageContract.Model

    @Before
    fun onSetup() {
        view = mock(PortfolioPageFragment::class.java)
        model = mock(PortfolioPageModel::class.java)
        presenter = PortfolioPagePresenter(view, model)
    }

    @Test
    fun `assert that onViewLoaded function calls model init and getPortfolio`() {
        presenter.onViewLoaded()

        verify(model).init(presenter)
        verify(model).getPortfolio()
    }

    @Test
    fun `assert that onViewDetached function calls model destroy`() {
        presenter.onViewDetached()

        verify(model).destroy()
    }

    @Test
    fun `assert that onPortfolioResponse call view updatePortFolio`() {
        val listOfStocks = mutableListOf<Stock>()
        presenter.onPortfolioResponse(listOfStocks)

        verify(view).updatePortfolio(listOfStocks)
    }

    @Test
    fun `assert that onPortFolioResponseError calls view showErrorMessage`() {
        presenter.onPortfolioResponseError()

        verify(view).showErrorMessage()
    }

    @Test
    fun `assert that onPortFolioEmptyResponse calls view showEmptyMessage`() {
        presenter.onPortfolioEmptyResponse()

        verify(view).showEmptyMessage()
    }

    @Test
    fun `assert that updateUserName calls view updateWelcomeMessage`() {
        `when`(model.getUserName()).thenReturn(USERNAME)
        presenter.updateUserName()
        val userName = model.getUserName()
        verify(view).updateWelcomeMessage(userName)
    }

    companion object {
        private const val USERNAME = "user"
    }

}