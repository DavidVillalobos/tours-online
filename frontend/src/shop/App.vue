<template>
  <div id="app">
    <Navbar/>
    <CartShopping/>
  </div>
</template>

<script>
import Navbar from '../index/components/NavBar'
import CartShopping from './components/CartShopping'

export default {
  name: 'App',
  components: {
    Navbar,
    CartShopping
  }
}
</script>

<style>

html, #app{
  background-image: url("../assets/background.png");
  background-size: cover;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-position: bottom;
}

#search{
  margin: 3% 3% 0% 3%;
}

</style>
