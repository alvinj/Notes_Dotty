package com.alvinalexander.notes.controller

import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.Scene
import javafx.scene.control._
import javafx.scene.input._
import javafx.stage.Stage
import javafx.util.Callback

import com.alvinalexander.notes.actions._
import com.alvinalexander.notes.view.{EditNotePane, GuiUtils, MainGridPane}
import com.alvinalexander.notes.Note
import com.alvinalexander.notes.db.Database
import com.alvinalexander.notes.utils.NoteUtils

/**
  * This is the main “controller” (as in MVC) for the application.
  */
class MainController

    private val db = Database()

    val mainGridPane = MainGridPane()

    // connect to these widgets so we can handle their events
    private val tagSearchButton  = mainGridPane.tagSearchButton
    private val textSearchButton = mainGridPane.textSearchButton
    private val searchField      = mainGridPane.searchField
    private val tableView        = mainGridPane.tableView
    private val addNoteButton    = mainGridPane.addNoteButton
    private val deleteNoteButton = mainGridPane.deleteNoteButton

    // event handlers
    // create a concrete instance of this one because it’s needed in two places
    val addNoteEventHandler = AddNoteEventHandler(tableView, db)
    tagSearchButton.setOnAction(TagSearchEventHandler(this, db))
    textSearchButton.setOnAction(TextSearchEventHandler(this, db))
    searchField.setOnAction(SearchFieldEventHandler(this, db))
    addNoteButton.setOnAction(addNoteEventHandler)
    deleteNoteButton.setOnAction(DeleteNotesEventHandler(tableView, db))
    // handle a double-click on a table tow
    tableView.setOnMouseClicked(EditNoteEventHandler(tableView, db))

    // read the database and populate the table
    val notes: Seq[Note] = db.getAll()
    setTableViewNotes(notes)

    def setTableViewNotes(notes: Seq[Note]): Unit =
        tableView.getItems.clear()
        for (n <- notes) tableView.getItems.add(n)

    def currentSearchText(): String = searchField.getText

    // get a reference to the main scene so we can add listeners to it
    def addKeystrokeHandlers(scene: Scene, stage: Stage): Unit =
        configureCommandFHandler(scene)
        configureCommandNHandler(scene)
        configureCommandMHandler(scene, stage)

    private def configureCommandNHandler(scene: Scene): Unit =
        val keyComboCommandN: KeyCombination = KeyCodeCombination(
            KeyCode.N,
            KeyCombination.META_DOWN
        )
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => {
            if (keyComboCommandN.`match`(event))
                addNoteEventHandler.handle(null)
        })

    private def configureCommandFHandler(scene: Scene): Unit =
        val keyComboCommandF: KeyCombination = KeyCodeCombination(
            KeyCode.F,
            KeyCombination.META_DOWN
        )
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => {
            if (keyComboCommandF.`match`(event))
                //Platform.runLater(() => searchField.requestFocus)
                searchField.requestFocus()
        })

    // minimize
    private def configureCommandMHandler(scene: Scene, mainStage: Stage): Unit =
        val keyComboCommandM: KeyCombination = KeyCodeCombination(
            KeyCode.M,
            KeyCombination.META_DOWN
        )
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => {
            if (keyComboCommandM.`match`(event))
                mainStage.setIconified(true)
        })







