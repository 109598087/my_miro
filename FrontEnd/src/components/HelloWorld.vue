<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <button>createStickyNote</button>
  </div>
<!--  template 下，只能有一個東西，不然會報錯!!!-->
<!--  <button>createStickyNote</button>-->
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    createStickyNote () {

    }
  }
}
</script>
