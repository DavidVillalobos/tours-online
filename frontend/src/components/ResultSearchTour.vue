<template>
  <div>
    <b-row v-for="(tour, idx) in tours" :key="idx">
      <b-col cols=12>
        <b-card class="tour" v-on:click="viewTour(tour)">
          {{ tour.text }}
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: 'ResultSearchTour',
  data() {
    return {
      tours: [
        { text: 'Barcelona' },
        { text: 'Paris' },
        { text: 'New York' },
        { text: 'Ciudad de Mexico' },
        { text: 'Londres' }
      ]
    }
  },
  methods: {
    viewTour: function (tour) {
      alert('Viendo el tour de ' + tour.text)
    }
  }
}

</script>
<style scoped>

.tour{
  margin-top: 10px;
}
</style>
