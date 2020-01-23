<template>
    <header class="header">
        <h1>MALIANTECS HOTELS</h1>
        <div class="home">
            <a class="home" href="main">Home</a>
          </div>
    </header>
</template>

<script>
    export default {
        name: "Header"
    };
</script>

<style scoped>

    .home {
    text-align: left;
    }

    .tablink {
      background-color: #555;
      color: white;
      float: left;
      border: none;
      outline: none;
      cursor: pointer;
      padding: 14px 16px;
      font-size: 17px;
      width: 25%;
    }

    /* Change background color of buttons on hover */
    .tablink:hover {
      background-color: #777;
    }

    /* Set default styles for tab content */
    .tabcontent {
      color: white;
      display: none;
      padding: 50px;
      text-align: center;
    }

    .header{
        background: #333;
        color: #fff;
        text-align: center;
        padding: 10px;
    }

    .header a{
        color: #fff;
        padding-right: 5px;
    }

</style>
