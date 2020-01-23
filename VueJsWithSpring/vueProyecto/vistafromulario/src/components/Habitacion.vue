<template>
  <div class="hello">

    <Header />

    <table width="50%" align="center" border="0">
          <tr>
                 <td><h2 align="left">Tipo de habitacion</h2></td>
                 <td><h2 align="center">Precio</h2></td>
                 <td><h2 align="right">Personas</h2></td>
          </tr>
          <tr>
                <td><h2 align="left">Familiar</h2></td>
                <td><h2 align="center">90.000</h2></td>
                <td><h2 align="right">5</h2></td>
                </tr>
        </table>

    <br><br><br>
    <img alt="imagen vacia" src="../assets/imagenVacia.jpg">
    <br><br>

    <button><router-link to="formularioHotel">Reservar ahora</router-link></button>

    <br><br><br><br>
    <Footer />
  </div>
</template>

<script>

import Header from './Header/Header';
import Footer from './Footer/Footer';

export default {

  name: 'Habitacion',
  components: {
        Header,
        Footer
  },
}
</script>

<style scoped>

</style>
