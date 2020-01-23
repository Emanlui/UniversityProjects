<template>
  <footer class="footer">
      <h1>Posted by: MalianTECS Hoteles</h1>
      <h1>Contact information: <a href="mailto:someone@example.com">
      MalianTECS.hotels@gmail.co.cr</a></h1>
  </footer>
</template>

<script>
    export default {
        name: "Footer"
    };
</script>

<style scoped>
    .footer{
        background: #333;
        color: #fff;
        text-align: center;
        padding: 10px;
    }

    .footer a{
        color: #fff;
        padding-right: 5px;
    }
</style>