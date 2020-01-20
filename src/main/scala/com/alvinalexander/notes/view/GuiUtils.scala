package com.alvinalexander.notes.view

import java.util.Optional

import javafx.application.Platform
import javafx.geometry.Rectangle2D
import javafx.scene.control.ButtonBar.ButtonData
import javafx.scene.control.{ButtonType, Dialog}
import javafx.stage.{Modality, Screen}
import com.alvinalexander.notes.Note

object GuiUtils

    def screenHeight(): Double = getScreenBounds().getHeight
    def screenWidth(): Double = getScreenBounds().getWidth
    def getScreenBounds(): Rectangle2D = Screen.getPrimary.getVisualBounds


    //TODO try to use a different Dialog type:
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html

    //TODO per comments on the following url, you can fix this problem by going into
    //TODO the macOS system preferences > Security > Accessibility, and adding the Notes
    //TODO app there. that “solves” the problem, though it’s already supposed to be
    //TODO fixed in Java 8. (I confirmed that this works.)
    //https://bugs.openjdk.java.net/browse/JDK-8211304

    def showNoteEditor(
        title: String = "Add Note",
        editNotePane: EditNotePane
    ): Option[Note] =

        // create the "Edit Note" dialog
        val editNoteDialog = Dialog[Option[Note]]()
        //val editNoteDialog = Dialog[ButtonType]()
        editNoteDialog.setTitle(title)
        val okButton = ButtonType("OK", ButtonData.OK_DONE)
        editNoteDialog.getDialogPane.getButtonTypes.addAll(okButton, ButtonType.CANCEL)
        editNoteDialog.getDialogPane.setContent(editNotePane)
        editNoteDialog.setResizable(true)
        editNoteDialog.initModality(Modality.APPLICATION_MODAL)

        // set initial focus
        Platform.runLater(() => editNotePane.notesField.requestFocus)

        editNoteDialog.setResultConverter(dialogButton => {
            if (dialogButton == okButton)
                val note = Note(
                    editNotePane.notesField.getText,
                    editNotePane.urlField.getText,
                    editNotePane.tagsField.getText
                )
                Some(note)
            else
                None
        })

        val result = editNoteDialog.showAndWait()
        val resultAsOption: Option[Note] = result.get
        resultAsOption










