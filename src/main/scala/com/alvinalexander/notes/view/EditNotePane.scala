package com.alvinalexander.notes.view

import javafx.event.EventHandler
import javafx.geometry.{HPos, Insets, VPos}
import javafx.scene.Node
import javafx.scene.control._
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.layout.{ColumnConstraints, GridPane, Priority}
import com.sun.javafx.scene.control.skin.TextAreaSkin

//TODO add an Add/Edit mode parameter to disable date fields when adding
class EditNotePane extends GridPane

    // widgets
    private val noteLabel = Label("Note:")
    private val urlLabel  = Label("URL:")
    private val tagsLabel = Label("Tags:")
    private val dateCreatedLabel = Label("Created:")
    private val dateUpdatedLabel = Label("Updated:")

    val notesField = TextArea()
    val urlField = TextField()
    val tagsField = TextField()
    val dateCreatedField = Label()
    val dateUpdatedField = Label()

    val saveButton = Button("Save")
    val cancelButton = Button("Cancel")

    // configure widgets
    notesField.setWrapText(true)
    GridPane.setVgrow(notesField, Priority.ALWAYS)
    urlField.setPromptText("optional")
    tagsField.setPromptText("foo, bar, baz")

    // add widgets to grid
    // child, columnIndex, rowIndex, colspan, rowspan
    this.addRow(0, noteLabel, notesField)
    this.addRow(1, urlLabel, urlField)
    this.addRow(2, tagsLabel, tagsField)
    this.addRow(3, dateCreatedLabel, dateCreatedField)
    this.addRow(4, dateUpdatedLabel, dateUpdatedField)

    // align the labels
    GridPane.setValignment(noteLabel, VPos.TOP)
    GridPane.setHalignment(noteLabel, HPos.RIGHT)
    GridPane.setHalignment(urlLabel,  HPos.RIGHT)
    GridPane.setHalignment(tagsLabel, HPos.RIGHT)
    GridPane.setHalignment(dateCreatedLabel, HPos.RIGHT)
    GridPane.setHalignment(dateUpdatedLabel, HPos.RIGHT)

    // more alignment
    val col1 = ColumnConstraints()
    col1.setPercentWidth(25)
    val col2 = ColumnConstraints()
    col2.setPercentWidth(75)
    this.getColumnConstraints().addAll(col1, col2)

    // things that affect size
    this.setPadding(new Insets(10))
    this.setHgap(10)
    this.setVgap(10)
    this.setMinWidth(600)
    this.setPrefWidth(600)
    notesField.setPrefRowCount(10)
    notesField.setPrefColumnCount(80)


    /**
      * force the textarea to treat the TAB key to mean "go to next field".
      * https://stackoverflow.com/questions/12860478/tab-key-navigation-in-javafx-textarea
      */
    notesField.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler[KeyEvent]() {
        override def handle(event: KeyEvent): Unit =
            if event.getCode == KeyCode.TAB then
                val node = event.getSource.asInstanceOf[Node]
                val skin = node.asInstanceOf[TextArea].getSkin.asInstanceOf[TextAreaSkin]
                skin.getBehavior.traverseNext
                event.consume
    })











