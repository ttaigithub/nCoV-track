<template>
  <div class="app-container">
    <baidu-map
      class="map"
      :center="baiduMap.center"
      :zoom="baiduMap.zoom"
      :scroll-wheel-zoom="true"
    >
      <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT" />
      <bm-city-list anchor="BMAP_ANCHOR_TOP_LEFT" />
      <bm-control style="margin: 10px 10px 10px 80px">
        <el-date-picker
          v-model="date"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions"
          size="mini"
          value-format="timestamp"
          @change="changeDate"
        />
      </bm-control>
      <bm-control anchor="BMAP_ANCHOR_BOTTOM_RIGHT">
        <el-card style="font-weight: bold">
          <span style="color: orange">疑似</span>
          <span style="color: purple">确诊</span>
          <span style="color: red">死亡</span>
          <span style="color: green">治愈</span>
          <span style="color: blue">可能传播者</span>
        </el-card>
      </bm-control>
      <bml-curve-line :points="track.suspected" stroke-color="orange" :stroke-weight="2" />
      <bml-curve-line :points="track.diagnosis" stroke-color="purple" :stroke-weight="2" />
      <bml-curve-line :points="track.dead" stroke-color="red" :stroke-weight="2" />
      <bml-curve-line :points="track.cure" stroke-color="green" :stroke-weight="2" />
      <bml-curve-line :points="track.possible" stroke-color="blue" :stroke-weight="2" />
    </baidu-map>
  </div>
</template>

<script>
import {
  queryLocationByTime
} from '@/api/informationEntry/data'
import { BmlCurveLine } from 'vue-baidu-map'

export default {
  name: 'Show',
  components: { BmlCurveLine },
  data() {
    return {
      date: [1577854477796, new Date().valueOf()],
      baiduMap: {
        center: { lng: 110, lat: 38 },
        zoom: 5,
        autoViewport: false
      },
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      track: {
        suspected: [],
        diagnosis: [],
        dead: [],
        cure: [],
        possible: []
      }
    }
  },
  computed: {},
  mounted() {
    // this.disclaimer()
    this.changeDate()
  },
  methods: {
    disclaimer() {
      const h = this.$createElement
      this.$msgbox({
        title: '免责声明',
        message: h('p', null, [
          h('div', null, '版权:同态团队作为资源的整理方，目前所展示的所有资源与信息均来自互联网的公开信息，包括但不限于个人路线图、定点医院信息、高速封路信息、口罩酒精等医疗资源货源情况等。版权及数据所有权均归原始所有者。\n'),
          h('div', null, '说明:本平台所提供的所有数据均来自于网络，仅供平台功能展示之用，所有关于与疫情相关的信息均以国家正式发布的信息为准。由于相关疫情信息不断更新，所有资源与数据仅供参考，本平台不予承担任何责任。任何涉及到商业目的的均不能使用，否则产生的一切后果由您自行承担，我们提供整理与功能应用但是不对任何资源负法律责任。'),
          h('div', null, '申明:任何使用本平台收集的资源与数据产生的不测后果，本平台不对此负任何责任。 转载时请保留本信息，谢谢。\n'),
          h('div', null, '感谢所有对新型冠状病毒疫情表示关注的热心人。我们会尽我们所能提供对应的技术支持与资源。同时请求各位谅解，由于制作时间匆忙，有不足之处还请及时指出并多多谅解。\n'),
          h('div', null, '大家众志成城，共克时艰，一起为祖国加油！\n'),
          h('div', { style: 'margin-top:32px' }, '2020年1月31日\n'),
          h('div', null, ' 同态疫情大数据应急团队')
        ]),
        confirmButtonText: '确定'
      })
    },
    changeDate() {
      this.queryTrack()
    },
    queryTrack() {
      const param = {
        data: JSON.stringify({
          startTime: this.date[0],
          endTime: this.date[1]
        })
      }
      queryLocationByTime(param)
        .then(res => {
          if (res.code !== 0) {
            this.$notify.warning('服务器繁忙，请重试')
          }
          if (res.data.length === 0) {
            this.$notify.warning('暂无数据，请稍后重试')
          }
          res.data['0'].forEach(item => {
            if (JSON.parse(item[0]).lat !== 0 && JSON.parse(item[1]).lat !== 0) {
              this.track.suspected.push(JSON.parse(item[0]))
              this.track.suspected.push(JSON.parse(item[1]))
            }
          })
          res.data['1'].forEach(item => {
            if (JSON.parse(item[0]).lat !== 0 && JSON.parse(item[1]).lat !== 0) {
              this.track.diagnosis.push(JSON.parse(item[0]))
              this.track.diagnosis.push(JSON.parse(item[1]))
            }
          })
          res.data['2'].forEach(item => {
            if (JSON.parse(item[0]).lat !== 0 && JSON.parse(item[1]).lat !== 0) {
              this.track.dead.push(JSON.parse(item[0]))
              this.track.dead.push(JSON.parse(item[1]))
            }
          })
          res.data['3'].forEach(item => {
            if (JSON.parse(item[0]).lat !== 0 && JSON.parse(item[1]).lat !== 0) {
              this.track.cure.push(JSON.parse(item[0]))
              this.track.cure.push(JSON.parse(item[1]))
            }
          })
          res.data['4'].forEach(item => {
            if (JSON.parse(item[0]).lat !== 0 && JSON.parse(item[1]).lat !== 0) {
              this.track.possible.push(JSON.parse(item[0]))
              this.track.possible.push(JSON.parse(item[1]))
            }
          })
        })
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  .map {
    width: 100%;
    height: calc(100vh - 120px);
  }
</style>
