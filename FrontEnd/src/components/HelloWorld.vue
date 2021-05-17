<template>
  <!-- <button @click="drawARect">畫圖</button> -->
  <div><button id="createStickyNoteButton" @click="createStickyNote()">Add New StickyNote</button>
    <canvas id="canvas" ref='board' ></canvas></div>

</template>

<script>
import { fabric } from 'fabric'
import axios from 'axios'

export default {
  data () {
    return {
      boardId: null,
      canvasContext: null,
      boardContent: null,
      canvas: null
    }
  },
  async mounted () {
    this.boardContent = this.getBoardContent()
    this.initCanvas()
    this.canvas.renderAll()
    // this.timer = setInterval(this.refreshCanvas, 10000)
  },
  methods: {
    async getBoardContent () {
      try {
        this.boardId = '6b9d885c-98f5-4756-9534-05abe8d76394'
        const res = await axios.get('http://localhost:8081/boards/' + this.boardId + '/content')
        this.drawStickyNote(res.data.textFigureDtos)
      } catch (err) {
        console.log(err)
      }
    },
    initCanvas () {
      this.canvas = new fabric.Canvas('canvas', {
        width: window.innerWidth,
        height: window.innerHeight
      })
    },

    drawStickyNote (textFigureDtos) {
      var _this = this
      for (let i = 0; i < textFigureDtos.length; i++) {
        const figure = textFigureDtos[i]
        var rect = new fabric.Rect({
          originX: 'center',
          originY: 'center',
          width: figure.style.width,
          height: figure.style.height,
          fill: figure.style.color
        })
        _this.canvas.add(rect)
      }
      this.canvas.renderAll()
    },
    refreshCanvas () {
      this.canvas.clear()
      this.getBoardContent()
    },
    async createStickyNote () {
      try {
        const res = await axios.post('http://localhost:8081/board/' + this.boardId + '/createStickyNote',
          {
            content: '',
            position: {
              x: 100,
              y: 100
            },
            style: {
              fontSize: 12,
              shape: 2,
              width: 100.0,
              height: 100.0,
              color: '#f9f900'
            }
          }
        )
        console.log(res)
      } catch (err) {
        console.log(err)
      }
      // this.refreshCanvas()
    }
  }
}
</script>
