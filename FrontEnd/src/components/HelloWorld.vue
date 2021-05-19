<template>
  <!-- <button @click="drawARect">畫圖</button> -->
  <div>
    <button id="createStickyNoteButton" @click="createStickyNote()">Add New StickyNote</button>
    <canvas id="canvas" ref='board' ></canvas>
  </div>

</template>

<script>
import { fabric } from 'fabric'
import axios from 'axios'

export default {
  data () {
    return {
      boardId: '65d7d0e1-ce2c-42a5-a603-3c8f1350a3de',
      canvasContext: null,
      boardContent: null,
      canvas: null
    }
  },
  methods: {
    initCanvas () {
      this.canvas = new fabric.Canvas('canvas', {
        width: window.innerWidth,
        height: window.innerHeight
      })
    },
    async getBoardContent () {
      try {
        const res = await axios.get('http://localhost:8081/boards/' + this.boardId + '/content')
        this.drawStickyNote(res.data.textFigureDtos)
      } catch (err) {
        console.log(err)
      }
    },
    drawStickyNote (textFigureDtos) {
      const _this = this
      for (let i = 0; i < textFigureDtos.length; i++) {
        const rect = new fabric.Rect({
          originX: 'center',
          originY: 'center',
          width: textFigureDtos[i].style.width,
          height: textFigureDtos[i].style.height,
          fill: textFigureDtos[i].style.color
        })
        let text = new fabric.IText(textFigureDtos[i].content, {
          fontSize: textFigureDtos[i].style.fontSize,
          originX: 'center',
          originY: 'center'
        })
        let group = new fabric.Group([rect, text], {
          id: textFigureDtos[i].figureId,
          content: textFigureDtos[i].content,
          top: textFigureDtos[i].position.y,
          left: textFigureDtos[i].position.x,
          subTargetCheck: true
        })
        _this.canvas.add(group)
      }
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
      this.refreshCanvas()
    }
  },
  async mounted () {
    this.initCanvas()
    this.boardContent = this.getBoardContent()
    this.canvas.renderAll()
    // this.timer = setInterval(this.refreshCanvas, 10000)
  }
}
</script>
