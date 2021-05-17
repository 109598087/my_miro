<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <canvas id="canvas" width="1920" height="600" ref="cvs"></canvas>
    <button>createStickyNote</button>
  </div>
<!--  template 下，只能有一個東西，不然會報錯!!!-->
<!--  <button>createStickyNote</button>-->
</template>

<script>
import { fabric } from 'fabric' // npm install --save fabric
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted () {
    let width = Math.max(document.documentElement.clientWidth, window.innerWidth || 0)
    let height = Math.max(document.documentElement.clientHeight, window.innerHeight || 0)
    this.canvas = new fabric.Canvas('canvas', {
      width: width,
      height: height,
      fireRightClick: true, // <-- enable firing of right click events
      stopContextMenu: true, // <--  prevent context menu from showing
      freeDrawingCursor: 'none'
    })
  },
  methods: {
    createStickyNote () {

    }
  }
}
</script>
