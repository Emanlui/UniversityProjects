
<template>

  <div >
  <Header />

    <div id="show-hotels" v-for="hotel in hotels" :key="hotel.id">
        <h1><a href="javascript:void(0)" v-on:click="goToHotel(hotel.id)">{{hotel.name}}</a></h1>
        <table style="width:100%">
        <tr>
            <td>Puntuacion</td>
            <td>Pais</td>
            <td>Region</td>
            <td>Imagen</td>
        </tr>
            <tr>
                <td>{{hotel.score}}</td>
                <td>{{hotel.country}}</td>
                <td>{{hotel.region}}</td>
                <td><img :src=hotel.url align="right"></td>
            </tr>
        </table>
    </div>

  <Footer />
  </div>
</template>

<script>
import Header from './Header/Header';
import Footer from './Footer/Footer';


export default {

  name: 'HotelesTodos',

  components: {
        Header,
        Footer
  },

  props: ['main'],

  data(){
    return {
        hotels : [],
        room : []
    }
  },
  methods: {

      goToHotel: function (hotelId) {


          let hotelIndex = this.hotels.findIndex( hotel => hotel.id == hotelId);

          let selectedHotel = this.hotels[hotelIndex];

          localStorage.setItem("selectedHotel", JSON.stringify(selectedHotel));

          let newArray = this.room.filter( room => room.hotel_id == hotelId);

          localStorage.setItem("selectedRoom", JSON.stringify(newArray));


          window.location.href = 'http://localhost:8081/main#/ElegirHotel';

      }

  },
   created() {

       localStorage.removeItem("fechas");
       localStorage.removeItem("reserva");
       localStorage.removeItem("selectedHotel");
       localStorage.removeItem("selectedRoom");

        this.$http.get('http://www.json-generator.com/api/json/get/cemCyNSvuG?indent=2').then(function(data) {
            this.room = data.body;

        });

        this.$http.get('http://www.json-generator.com/api/json/get/bVKSVzMguq?indent=2').then(function(data){
                    this.hotels = data.body;
                });
   },
}

</script>

<style scoped>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 5px;
  text-align: left;
}
</style>