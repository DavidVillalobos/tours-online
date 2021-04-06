<template>
  <div>
    <b-card bg-variant="dark">
      <b-card bg-variant="light">
            <b-row class="text-left"> 
            <h5>
              {{tour.city.name}}: {{tour.name}}
            </h5>
            </b-row> 
            <b-row class="text-left"> 
              <b-col cols=3>
                <b-form-rating v-model="tour.rating" size="sm" variant="warning" show-value-max readonly show-value no-border></b-form-rating>
              </b-col>
              <b-col>
                <b-badge variant="primary">{{ tour.reviews }} Opiniones </b-badge> 
              </b-col>
            </b-row>
            <b-row class="justify-content-md-center">
              <b-col cols=10>
                  <b-carousel
                    id="carousel-1"
                    :interval="5000"
                    controls
                    indicators
                    img-width="1024"
                    img-height="480"
                  >
                    <b-carousel-slide id="image" img-src="http://localhost:8001/only-image?id=1">
                    </b-carousel-slide>
                    <b-carousel-slide id="image" img-src="http://localhost:8001/only-image?id=2">
                    </b-carousel-slide >
                    <b-carousel-slide id="image" img-src="http://localhost:8001/only-image?id=3">
                    </b-carousel-slide>
                    <b-carousel-slide id="image" img-src="http://localhost:8001/only-image?id=4">
                    </b-carousel-slide>
                  </b-carousel>
              </b-col>
            </b-row>
            <b-row class="justify-content-md-center">
              <b-col cols=6>
                {{tour.description}}
              </b-col>
              <b-col cols=4>
                <b-card>
                  <b-row>
                  <b-col cols=5>
                  Desde
                  <h4><strong>{{tour.price}}$ </strong> </h4> 
                  por persona
                  </b-col>
                  <b-col cols=7>
                  <b-button pill block variant="primary" >Reserva ya</b-button>
                  </b-col>
                  </b-row>
                </b-card>
              </b-col>
            </b-row>
            <b-row>
            <h4><strong>Informacion general</strong></h4>
            <b-icon-clock-history></b-icon-clock-history>
                Duración : {{ tour.duration }} 
            </b-row>
      </b-card>
    </b-card>
    <b-card bg-variant="dark">
      <template v-if="tour.comments != 0">
        <b-row v-for="(comment, idx) in tour.comments" :key="idx">
          <b-col cols=12>
            <b-card class="tour">
              <b-form-rating v-model="comment.rating" size="sm" variant="warning" show-value-max readonly show-value no-border></b-form-rating>
              {{comment.description}}
            </b-card>
          </b-col>
        </b-row>
      </template>
    </b-card>
  </div>
</template>

<script>

export default {
  name: 'Tour',
  data() {
    if(!this.$session.exists() || !this.$session.get('tour')){
      window.location.href = 'http://localhost:8002';
    }
    let tourSession = this.$session.get('tour')
    return {
      tour: tourSession,
      messageAlert: "",
      showAlert: 0,
      alertvariant: "",
      secShowAlert: 5,
      slide: 0,
      sliding: null
    }
  },
  mounted: async function(){
      let tourId = this.tour.id;
      let userId = 0;
      if(this.$session.get('user')){
        userId = this.$session.get('user').id;
      }
      try {
        const response = await fetch(
          'http://localhost:8001/tour?' +
          'id=' + tourId + 
          '&&id_user=' + userId +
          '&&simpleTour=false'
        , {method: 'GET'});
        this.$session.set('tour', (await response.json()));
        this.tour = this.$session.get('tour')
      } catch (err) {
        console.log(err)
      }
  },
  methods: {
    countDownChanged(dismissCountDown) {
        this.showAlert = dismissCountDown
    },
    hexToBase64(str) {
      return 'data:image/jpeg;base64,' + str;
    },
    onSlideStart(slide) {
        this.sliding = true
        console.log(slide)
    },
    onSlideEnd(slide) {
      this.sliding = false
      console.log(slide)
    }
  }
}

</script>

<style scoped>
#result-search{
  margin-top: 5px;
}

.tour{
  margin-top: 8px;
}

.tour:hover{
  background-color: rgba(255, 255, 255, 0.926)
}

.like-button{
  position:absolute; 
  z-index:1;
  width: 20%;
  height: 20%;
  margin-top: 1%;
  margin-left: -16%;
}

.alert-content{
  position:absolute; z-index:1;
  margin-left: 40%;
  margin-top: 1%;
}

#image{
  min-width: 100px;
  min-height: 100px;
  max-height: 500px;
  max-width: 900px;
  width: auto;
  height: auto;
}
</style>
