<template>
  <div class="app-container">
    <el-table
      class="table-body"
      :data="tableData"
      row-key="id"
      :height="tableHeight"
    >
      <el-table-column
        v-for="item of tableHeader"
        :key="item.key"
        :prop="item.key"
        :label="item.label"
        :width="item.width"
        :min-width="item.minWidth"
      />
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'Show',
  components: {},
  data() {
    return {
      tableHeader: [
        {
          label: '省份',
          key: 'province',
          width: '200'
        },
        {
          label: '路况',
          key: 'roadCondition',
          minWidth: '200'
        }
      ],
      tableData: [
        {
          province: '山东',
          roadCondition: ' G15沈海高速公路日照北收费站因交通管制沈阳方向出入口及海口方向出入口完全封闭，请过往车辆择路绕行。 G1511日兰高速公路济宁、济宁北、济宁西、嘉祥收费站双向入口因雾完全封闭，请过往车辆择路绕行。\n' +
          ' G18荣乌高速公路垦利北、陈庄、利津、沾化东、沾化西收费站乌海方向出入口及荣成方向出入口因交通管制完全封闭，请过往车辆择路绕行。\n' +
          ' G2京沪高速公路乐陵南、乐陵南、临邑收费站因交通管制，上海方向出入口及北京方向出入口完全封闭，请过往车辆择路绕行。\n' +
          ' G22青兰高速公路李海务、河店、朱老庄、东阿、魏庄收费站兰州方向入口及青岛方向入口因团雾完全封闭，请过往车辆择路绕行。\n' +
          ' G22青兰高速公路鲁村、张家坡、沂源收费站双向出入口完全封闭，请过往车辆择路绕行。 ​​​​\n' +
          ' G25长深高速公路古城、大高、滨州北、滨州南、阳河、青州北收费站因高速交警实施交通管制深圳方向出口及长春方向出口临时封闭，请过往车辆择路绕行。\n' +
          ' G2516东吕高速滨州东、惠民、惠民西、盐窝收费站东营方向出入口及吕梁方向出入口完全封闭，请过往车辆择路绕行。\n' +
          ' G35济广高速公路长清、孝里、平阴、平阴南、东平、梁山、嘉祥西收费站广州方向入口及济南方向入口因雾完全封闭，请过往车辆择路绕行。\n' +
          ' G3511菏宝高速公路菏泽东收费站因交通管制双向出入口完全封闭，请过往车辆择路绕行。\n' +
          ' S1济聊高速公路齐河南、晏城、齐河西收费站双向入口因雾完全封闭，茌平、茌平西、聊城开发区、聊城东、聊城、聊城西、冠县东、冠县济南方向入口因雾完全封闭，请过往车辆择路绕行。\n' +
          ' S12滨德高速公路德州东、德州西、无棣东收费站德州方向出口及滨州方向出口完全封闭，请过往车辆择路绕行。\n' +
          ' S33济徐高速公路汶上西、汶上南收费站徐州方向入口及济南方向入口因雾完全封闭，请过往车辆择路绕行。\n' +
          ' S29滨莱高速公路和庄、淄博新区、文昌湖、博山、高青收费站双向出入口完全封闭，请过往车辆择路绕行。\n' +
          ' G2001济南绕城高速公路机场收费站出口因车流量大造成主线压滞（防疫人员及交警对收费站出口车辆进行检查），请过往车辆减速慢行或择路绕行\n' +
          ' G3京台高速平原南、滕州南站双向出口临时关闭，\n' +
          ' G2001济南绕城高速西线齐河生态城站双向出口临时关闭；\n' +
          ' G20青银高速淄博、临淄东站双向出入口临时关闭，邹平西、青州东站双向出口临时关闭；\n' +
          ' G25长深高速博兴北站双向出入口临时关闭；\n' +
          ' G0321德上高速鄄城北、鄄城南、菏泽北站双向出入口临时关闭；\n' +
          ' G1511日兰高速菏泽南、牡丹站双向出入口临时关闭；\n' +
          ' G1815潍日高速五莲北、街头、陈疃北站双向出入口临时关闭。S38岚曹高速枣庄新城、峄城南站双向出口临时关闭。'
        },
        {
          province: '上海',
          roadCondition: '高速封路： G2京沪（江苏G2花桥）、G15朱桥、G60沪昆、G15沪浙、S36亭枫、G50沪渝、S26沪常、S32申嘉湖、G40崇启'
        },
        {
          province: '北京',
          roadCondition: '无官方消息'
        },
        {
          province: '辽宁',
          roadCondition: '"封路：1.G1京哈高速公路，10个收费站：双庙子、铁岭南、清水台、蒲河、高花东、高花西、茨榆坨、光辉、葫芦岛东、沙后所；5个服务区：万家、高花、腰堡、铁岭、昌图。\n' +
          '2.G11鹤大高速公路，19个收费站：青山沟、牛毛坞、白石砬子、宽甸南、毛甸子、五龙山东、五龙山西、丹东北、前阳、马家店、栗子房、吴炉、大郑、明阳、城子坦、杨树房、杏树屯、得胜、十里岗南；4个服务区：大孤山、皮口、宽甸、云峰山。\n' +
          '3.G15沈海高公路，15个收费站：苏家屯南、十里河、辽阳、汤岗子、沙岗子、李官、太平湾、老虎屯、炮台、海湾北、周水子、营城子、长城、三涧堡、旅顺新港；1个服务区：熊岳。\n' +
          '4.G16丹锡高速公路，12个收费站：西土城、洋河、岫岩南、孤山、析木、海城南、感王、旗口、班吉塔、大庙、老建平、惠州；2个服务区：岫岩、大庙\n' +
          '5.G25长深高速公路，7个收费站：康平北、方家屯、包家屯、王府、马友营、杨树湾、北炉；5个服务区：彰武、那四、马友营、桃花吐、牛河梁。\n' +
          '6.G91辽中环线高速公路，16个收费站：腰堡北、李千户、三岔子、东洲、石文、北台、高官、弓长岭、小屯、黄泥洼、辽中南、满都户、金五台、公主屯、陶屯、三面船；3个服务区：首山、辽中南、李千户。\n' +
          '7.G1113丹阜高速公路，11个收费站:五龙背、草河口、下马塘、桥头、本溪南、本溪北、响山、边牛、杨千户、佟沟新城、老边；3个服务区：刘千户、石桥子、凤城。\n' +
          '8.G1212沈吉高速公路，7个收费站：沈阳东、世博园、抚顺、南口前、北三家、英额门、草市；2个服务区：抚顺、草市。\n' +
          '9.G1501沈阳绕城高速，8个收费站：张士东、宁官东、宁官西、谟家堡、下河湾、雪莲街、古城子南、古城子北。\n' +
          '10.G2511新鲁高速公路，3个收费站：新民北、于家、彰武北；1个服务区：新民。\n' +
          '11.G2512阜锦高速公路，1个收费站：七里河。\n' +
          '12.S2沈康高速公路，2个收费站：依牛堡子、财湖；1个服务区：康平北\n' +
          '13.S10抚通高速公路，4个收费站：上夹河、木奇、永陵、旺清门南北匝道；2个服务区：南杂木、永陵。\n' +
          '14.S12皮长高速高速公路，6个收费站：兴隆堡、普兰店东、普兰店西、松木岛、复州湾、邢屯；1个服务区：普兰店。\n' +
          '15.S14西开高速公路，4个收费站：金沟子、威远堡、郜家店、安民。\n' +
          '16.S17平康高速，2个收费站：鴜鹭树、四面城；1个服务区：宝力。\n' +
          '17.S19庄盖高速公路，5个收费站：庄河西、光明山、长岭、小石棚、盖州南。\n' +
          '18.S20灯辽高速公路，3个收费站：灯塔南、佟二堡、茨榆坨北\n' +
          '19.S21奈营高速公路，6个收费站：玉龙新城、海棠山、白厂门、大市、沟帮子、营口北；1个服务区：北镇。\n' +
          '20.S23大窑湾疏港路，2个收费站：关家店、二十里堡。\n' +
          '21.S26兴建高速公路，3个收费站：白塔、药王、巴什罕 ；1个服务区：药王庙。\n' +
          '22.S29盘锦港疏港路，1个收费站：辽东湾。\n' +
          '23.S33 大东港疏港路，1个收费站：大东港。24.S326丹东港疏港路，2个收费站：丹东西、丹东新区 。"'
        },
        {
          province: '陕西',
          roadCondition: '"1.绕城高速雁塔、长安西、丈八出口封闭,杏园南、杏园北出入口封闭。\n' +
          '2.西铜高速铜川新区至黄堡之间所有入口延安方向封闭,所有车辆由铜川主线站分流。\n' +
          '3.西耀高速新兴至花石崖之间所有出口封闭,车辆可由收费站内广场掉头通行至铜川新区出口下高速。\n' +
          '4.西延高速彭镇、金锁关、川口出口封闭。5.铜旬高速所有出口暂时封闭,旬邑方向的车辆绕行咸旬高速。\n' +
          '6.西镇高速营盘、东坪、小河出入口封闭。\n' +
          '7.西渭高速豁口、港口出口,兵马俑出入口封闭,官厅立交方向西渭引线段转方家村立交K0处临时交通管制,西安方向潼关治超站交通管制,所有入陕车辆需在潼关治超站检测。\n' +
          '8.西禹高速港务区出入口封闭,前往西安方向的车辆由龙门工业园出口检测\n' +
          '9.西汉高速三星入口,涝峪出入口,纸坊、朱雀、鄠邑出口封闭,汉中方向金水出口封闭\n' +
          '10.渭玉高速桥南、蔺店、厚镇、卤阳湖出口封闭。11.机场专用高速北环路出入口封闭。\n' +
          '12.西咸北环线高速高陵西、永乐东出口,新市、店张、太平出入口封13.西宝高速常兴出入口封闭。\n' +
          '14.西商高速华胥、玉山出口,腰市、板桥出入口封闭。\n' +
          '15.西蓝高速空工出入口封闭。16.蓝商高速辋川、蓝关出口,商洛西、南城子、杨斜出入口封闭。\n' +
          '17.商界高速过风楼、金丝峡、竹林关、棣花、商洛东出入口封闭。\n' +
          '18.水阳高速凤凰西至色河铺之间所有出入口封闭。19.商漫高速闫村至漫川匝道之间所有出入口封闭。\n' +
          '20.白泉高速石泉、池河出口进行检测,对不符合要求的车辆劝返。21.汉川高速马道、喜神坝、南湖出口封闭\n' +
          '22.宝鸡高速南寨、水沟出入口封闭。23.汉宁高速龙亭、上元观、胡家坝、韩家坝出口封闭。\n' +
          '24.西略高速五郎坪、茶店、新街子、汉中北、盐井、沙河、茶镇出口封闭。\n' +
          '25.榆靖高速榆林西、西左界、黄蒿界、王则湾、孟家湾、小壕兔出口封闭。\n' +
          '26.神府高速石马川出口禁止大型货车通行,神木北、永兴出口对所有车辆劝返。\n' +
          '27.吴靖高速杨桥畔、双城、魏家楼、中角、辛家沟出入口封闭。28.靖王高速靖边西至砖井之间所有出入口封闭。\n' +
          '29.延靖高速靖边南至天赐湾之间所有出入口封闭。\n' +
          '30.神佳米高速高家堡、安崖出入口封闭。31.榆绥高速古城滩、鱼河、镇川出口,四十里铺出入口封闭。\n' +
          '32.延志吴高速侯市、志丹东出入口封闭。33.榆佳高速王家砭、通镇出入口封闭。\n' +
          '34.吴定高速吴起北、新安边、杨井出入口封闭。35.黄延高速富县西、羊泉出入口封闭。\n' +
          '36.宜富高速张家湾、直罗、张村驿出入口封闭。37.榆神高速大保当、锦界、西沟、店塔出入口封闭。\n' +
          '38.绥延高速淮宁湾、清涧南、王家坪出口封闭。\n' +
          '39.咸旬高速兴隆、淳化北、十里塬、土桥出入口封闭。"'
        },
        {
          province: '吉林·',
          roadCondition: '"　封路：1、珲乌高速平安站、到保站、太山站、青山站、舍力站、松原西站、八道站、凉水站;\n' +
          '　　2、白洮高速白城西站、陶家站;\n' +
          '　　3、双嫩高速坦途站、东屏站、镇赉北站、黑水站;\n' +
          '　　4、铁科高速松原大路站;\n' +
          '　　5、牡延高速百草沟站;\n' +
          '　　6、大广高速松原南A站;\n' +
          '　　7、长长高速磐石南站;\n' +
          '　　8、集双高速梅河东站、梅河南站、通化南站;\n' +
          '　　9、营东高速那丹伯站;\n' +
          '　　10、沈吉高速一座营站、东梅站、大阳站、山城站;\n' +
          '　　11、鹤大高速雁鸣湖站、官地站、敦化北站、敦化西站、敦化南站。"'
        },
        {
          province: '甘肃',
          roadCondition: '"封路：G30连霍高速：瓜州、西双塔、嘉峪关、总寨、下河清、赤金、清水、清泉、梧桐泉、张掖丹霞、张掖西、老寺庙、东乐、山丹东、丰城堡、马营口、永昌、丰乐、古丰、武威南、中堡、定西、石门、利桥、桃花坪、东岔收费站出、入口及安门收费站出口。\n' +
          'G3017金武高速：装备园收费站出、入口。\n' +
          'G22青兰高速：原兰州东、大山川、西巩驿、巉口、肖金、驿马、合水、连家砭、太白收费站出、入口及长庆桥、罗汉洞、白水收费站出口。\n' +
          'G2012定武高速：寺滩收费站出、入口。\n' +
          'G1816乌玛高速：合作北收费站出口。\n' +
          'S17金永高速：金昌收费站出口。\n' +
          'G75兰海高速：安家嘴、三十里墩、玉井、武都西、桔柑、琵琶收费站出、入口。\n' +
          'G8513平绵高速：马街、佛崖西、佛崖东收费站出、入口。'
        },
        {
          province: '江苏',
          roadCondition: '"封路：G2京沪高速，关闭泰州医药城、靖江、江阴、阳澄湖、陆家双向出口，以及淮安北、泾河、界首、真武、大桥、浦头双向出、入口；\n' +
          'G3京台高速，关闭汉王、徐州西、贾汪至柳新双向出口；\n' +
          'G15沈海高速，关闭沙溪双向出口、上海往苏州方向的太仓出口；以及灌云北、新坝、连云港北双向出、入口；\n' +
          'G1515盐靖高速，关闭青墩、张庄、新街、溱潼双向出口；\n' +
          'G1516盐洛高速，关闭秦南、建湖东、海丰、大丰港双向出口，以及马甸、车桥、凌桥双向出、入口；\n' +
          'G1521常嘉高速，关闭李市、吴淞江工业园、莘塔双向出口、以及白茆双向出、入口；\n' +
          'G1522常台高速，关闭董浜、阳澄湖、黎里双向出口；\n' +
          'G25长深高速，关闭句容西、曹山、溧阳西、溧阳东、鲸塘、丁山双向出口，以及宋跳、灌云西、高沟、涟水、丁集、五里牌、铁山寺双向出、入口；\n' +
          'G2513淮徐高速，关闭睢宁西双向出口，以及泗阳、宿迁西双向出、入口；\n' +
          'G30连霍高速，关闭徐州南、东探至邳州西、新沂双向出口，以及云台、东海西双向出、入口；\n' +
          'G40沪陕高速，关闭枣林湾、汤汪、广陵、季市、如皋港、九华、启东东双向出口，以及启东南双向入口；\n' +
          'G4011扬溧高速，关闭镇江西至后周双向出口；G42沪蓉高速，关闭丹阳东、常州、罗墅湾、玉祁双向出口；\n' +
          'G4221沪武高速，关闭天王、茅山、金坛东、滆湖、戚墅堰、霞客、江阴新桥、凤凰以及常熟双向出口；\n' +
          'G50沪渝高速，关闭北厍、横扇、七都双向出口；\n' +
          'S9苏绍高速，关闭渭塘、黄埭、东桥、通安、光福以及西山双向出口；\n' +
          'S19通锡高速，关闭新安、无锡新区至张家港东、三兴双向出口；\n' +
          'S28启扬高速，关闭如皋东至马塘、曲塘、海安南、海门港、吕泗港、南阳双向出口，以及郭村、丁伙双向出、入口；\n' +
          'S35泰镇高速，关闭海陵、大泗、镇江新区南、丹阳新区、丹阳北双向出口；S39江宜高速，关闭丹阳新桥、春江、龙城大道、武进双向出口；\n' +
          'S48沪宜高速，关闭漕桥、屺亭、阳澄湖北、岳王主线双向出口；\n' +
          'S49新扬高速，关闭宿迁北、宿迁南至泗洪、黄花塘、盱眙北、刘集双向出、入口，以及新沂南、马陵山双向出口；\n' +
          'S58沪常高速，关闭张浦、甪直南、郭巷双向出口；S65徐明高速，关闭双沟东双向出口；S69济徐高速，关闭全线双向出口；\n' +
          'S75阜兴泰支线，关闭兴化周庄、俞垛双向出口；\n' +
          'S79南通支线，关闭平潮双向出、入口；S80太仓港北疏港高速，关闭璜泾南双向出口；S82张家港疏港高速，关闭晨阳、张家港保税区双向出口；\n' +
          'S83无锡支线，关闭望亭双向出口；S85溧阳支线，关闭江苏中关村、上黄、长荡湖双向出、入口；S92金湖支线，关闭东阳双向出、入口。\n' +
          '\n' +
          '"'
        },
        {
          province: '浙江',
          roadCondition: '"G15沈海高速蒲岐、七都、萧江出口；\n' +
          'G1513温丽高速桥头出口；\n' +
          'G1523甬莞高速机场、瑞安东、鳌江、平阳坑、高楼出口；\n' +
          'S10温州绕城高速陶山、荆谷、万全出口；\n' +
          'S26诸永高速古庙、花坦出口。"'
        },
        {
          province: '安徽',
          roadCondition: '"合徐高速：宿州南出入口封闭，淮北、宿州出口对外省车辆劝返。\n' +
          '京台高速：陵阳出入口封闭。\n' +
          '连霍高速：萧县东出入口封闭，张庄寨出入口封闭。\n' +
          '界阜蚌高速：界首往河南方向入口封闭、出口对外省车辆劝返，界首往蚌埠方向出入口外省车辆禁止上下道；利辛出入口对鄂A车辆劝返，蒙城、利辛东、太和出口对外省车辆劝返，怀远西出口对湖北省车辆劝返，利辛西出入口对湖北省车辆劝返。\n' +
          '盐洛高速：铁佛、宿州东、淮北南出入口封闭，宿州北出口对外省车辆劝返，濉溪出口对非皖F车辆劝返。\n' +
          '徐明高速：泗县西出入口封闭。\n' +
          '德上高速：砀山北入口封闭，涡阳北、涡阳东出口对外省车辆劝返，池州西出入口封闭。\n' +
          '济广高速：香隅出入口封闭。\n' +
          '六武高速：武汉往六安方向的车辆在长岭关服务区进行疫情监测。\n' +
          '沪武高速：含山南、郑蒲港、太白岛、采石矶出口对外省车辆劝返。\n' +
          '沪渝高速：九华山北出入口封闭。\n' +
          '宁芜高速：当涂、马鞍山北、马鞍山南出口对外省车辆劝返，太白出口对所有车辆劝返，芜湖南、峨山路出口对非皖B车辆劝返。\n' +
          '天天高速：香泉、和县北出入口封闭，和县西出口对外省车辆劝返。\n' +
          '黄塔桃高速：岭南出入口封闭，龙田出口封闭。\n' +
          '黄浮高速：休宁、牯牛降、闪里出入口封闭。\n' +
          '安东高速：东流出入口封闭。\n' +
          '宣桐高速：孙埠、宁国北、宁国、中溪出口对外省车辆劝返，千秋关入口对外省车辆劝返。\n' +
          '溧宁高速：杨滩出口对外省车辆劝返。\n' +
          '宁宣高速：狸桥出口对外省车辆劝返。"'
        },
        {
          province: '湖南',
          roadCondition: '"2020年1月30日15时20分，杭长高速长永段长沙收费站出口因防疫检查造成交通通行缓慢，交通恢复正常通行时间待定。\n' +
          '2020年1月30日14时24分，长张高速长益段益阳收费站附近以西K43处西往东因三车追尾造成交通通行缓慢，至14时47分已恢复正常通行。\n' +
          '2020年1月30日14时24分，长张高速长益段益阳收费站附近以西K43处西往东因三车追尾造成交通通行缓慢，目前事故正在处理当中，交通恢复正常通行时间待定。\n' +
          '2020年1月30日14时23分，张南高速龙永段龙山收费站附近以北K156处北往南因防疫检查造成交通通行缓慢，交通恢复正常通行时间待定。\n' +
          '2020年1月30日13时，长张高速长益段宁乡收费站附近以西K36至K39处西往东因车流量大造成交通通行缓慢，至14时18分已恢复正常通行。\n' +
          '2020年1月30日14时，二广高速永蓝段长铺收费站附近以南K2415处北往南因广东境内丰阳服务区防疫检查造成交通通行缓慢，交通恢复正常通行时间待定。\n' +
          '2020年1月30日13时17分，长张高速益常段幸福渠收费站入口因主线车流量大实行交通管制，交通管制解除时间待定。\n' +
          '2020年1月30日13时02分，长张高速长益段金洲、宁乡收费站入口因主线车流量大实行交通管制，交通管制解除时间待定。\n' +
          '：2020年1月30日13时，长张高速长益段宁乡收费站附近以西K36至K39处西往东因车流量大造成交通通行缓慢，交通恢复正常通行时间待定。\n' +
          '2020年1月30日12时48分，平洞高速益阳南线段金盆山收费站入口因主线车流量大实行交通管制，交通管制解除时间待定。\n' +
          '2020年1月30日12时43分，许广高速岳望段汨罗北收费站出入口因防疫检查实行交通管制，交通管制解除时间待定。\n' +
          '2020年1月30日12时42分，长张高速长益段益阳、泉交河、朝阳收费站入口因主线车流量大实行交通管制，交通管制解除时间待定。\n' +
          '路况信息：2020年1月30日12时20分，京港澳高速耒宜段宜章收费站附近以南K1837至K1841处北往南因广东境内粤北服务区防疫检查造成交通通行缓慢，交通恢复正常通行时间待定。"'
        },
        {
          province: '云南',
          roadCondition: '"昆明：省公安厅交警总队高速公路支队、昆明市公安交警部门启动防疫设卡检查工作，对昆安、昆石、昆曲、昆武4条高速公路入昆方向，以“春运交通安全服务站”的形式，依托收费站或者服务区，设置疫情检查卡点，对上行线入昆方向的车辆进行检查。\n' +
          '玉溪：自2020年1月27日22点起，对昆玉高速、玉元高速、晋红高速除昆玉高速梅园入口、晋红高速大营街入口、玉元高速研和入口外，所有进入红塔区的高速入口实行单向封闭，仅保留出口。恢复通行时间另行通知。\n' +
          '澄江：从2020年1月26日开始澄江在呈澄高速收费站、老澄马线中关、老澄川线、澄阳线设置四个卡点，对所有外地车辆进行劝返。\n' +
          '易门：从2020年1月27日起，所有外地车辆（人员）禁止进入易门县境内。驾驶员和同乘人员属于易门籍的凭居民身份证进入，但必须进行体温检测和车辆（人员）信息登记后方可进入易门县境内。\n' +
          '大理：从2020年1月26日10:00起对楚大高速公路凤仪、大理收费站出口进行关闭，对大丽高速公路海东、挖色、双廊、邓川、牛街收费站出口进行关闭。出站车辆（仅限大理号牌车辆）请从大丽高速公路洱源收费站、云浪收费站，大永高速公路上收费站出站。\n' +
          '丽江：自2020年1月26日16：00起，对大丽高速公路拉市收费站出口进行关闭，对大丽高速公路丽江西收费站出口的外省及外州（市）车辆实行劝返。对蓉丽（丽攀）高速公路华坪路段入丽的外省及外州（市）车辆实行劝返，对鹤庆进入古城、剑川进入玉龙、宾川进入永胜、四川省凉山州进入宁蒗等的外省及外州（市）车辆实行劝返。\n' +
          '临沧：从2020年1月27日起，临沧市已在所有进入临沧市域内通道上设置留验检查站，对计划进入临沧市的非云S牌照车辆、非临沧户籍人员等非必须进入临沧车辆、人员进行劝返，请广大游客给予理解、支持和配合。\n' +
          '文山：从1月27日22时起，文山市公安局交警大队联合市局相关部门抽调警力、文山高速大队和市卫生健康、防疫等部门工作人员，在文山东互通、文山西互通、马塘、德厚、薄竹5个高速公路出入口，开展24小时疫情防控卡口查缉。\n' +
          '墨江：自2020年1月28日00时始，对进入墨江的非云J牌照车辆和非普洱户籍人员一律进行劝返，云J牌照车辆实行监测点身体状况监测，人员严格排查、甄别、未发现异常允许入城，发现异常者就地采取相应隔离保护措施。"'
        },
        {
          province: '广西',
          roadCondition: '无官方消息'
        },
        {
          province: '广东',
          roadCondition: '广佛肇往广州华南方向，江口省界治安检查站建立防疫检查点；封闭主线；途径车辆检查后再进入主线行驶。深汕西高速往深圳方向，长沙湾站至鮜门站，缓行通过；'
        },
        {
          province: '重庆',
          roadCondition: '"G5013渝蓉高速大足西收费站出入口因疫情，实施管制。2020-01-30 08:27： 1月30日8时22分，G50沪渝高速石忠段下行（进城）方向冷水至河源冷水服务区K1503+000因执法队设置疫情检测点，对冷水服务区非渝籍车辆进行劝返，封闭主线第一车道、第二车道和应急车道，渝籍车辆可通过冷水服务区绕行至主线正常通行。\n' +
          'G75兰海高速渝黔段綦江、雷神店、古剑山、东溪、赶水收费站出口因疫情，实施管制。\n' +
          'S0101三环高速江綦段永新、北渡收费站出口因疫情，实施管制，綦江南收费站出口车流量大。\n' +
          'G42沪蓉高速万云段天城收费站出口车流量大，垫忠段上行方向明月山隧道至牡丹源车流量大。\n' +
          'G65包茂高速渝邻段下行方向高嘴至古路服务区车流量大。\n' +
          'G85银昆高速渝广段上行方向香龙至双槐服务区车流量大。\n' +
          '1月30日11时40分，S0101三环高速江綦段綦江南收费站出口车流量较大，拥堵600米，预计通行时间20分钟。 ​​​​\n' +
          '10:00， S0101三环高速江綦段永新、北渡收费站出口因疫情，实施管制G75兰海高速渝黔段綦江、雷神店、古剑山、东溪、赶水收费站出口因疫情，实施管制。 ​​​​\n' +
          '1月30日9时50分，G50沪渝高速石忠段下行（进城）方向冷水至河源冷水服务区K1503+000因执法队设置疫情检测点，对冷水服务区非渝籍车辆进行劝返，封闭主线第一车道、第二车道和应急车道，渝籍车辆可通过冷水服务区绕行至主线正常通行。\n' +
          'G65包茂高速渝湘段黔江西、黔江南、濯水收费站入口大雾管制。S0101三环高速江綦段永新、北渡收费站出口因疫情，实施管制。 ​​​​\n' +
          'G75兰海高速渝黔段因配合綦江区政府对新型冠状病毒感染的肺炎疫情防控需要，现綦江站、雷神店站、古剑山站、东溪站、赶水站对出口所有车辆进行劝返，到綦江的车辆可以在安稳或綦江南下道。"'
        },
        {
          province: '河北',
          roadCondition: '"因金山岭防疫检查，后方缓行2公里，目前京承高速偏桥、滦平两个站口暂时关闭。请前往北京的司机朋友，选择绕行国道！ ​​​​\n' +
          '新元高速(S9902):因交通管制,石家庄站东口上道口K274+614处双向车辆缓慢通行。\n' +
          '唐津高速(S0105):因国道养护施工,唐山东站上、下道口双向关闭。\n' +
          '迁西支线(S53):因交通管制,新庄子站下道口迁西方向，新庄子站唐山方向关闭。\n' +
          '承围高速(S50):因交通管制,围场南站围场方向关闭。\n' +
          '大广高速(G45):因养护施工,饶阳站下道口双向超宽车辆无法下道。"'
        },
        {
          province: '海南',
          roadCondition: '对进入文昌市道路路口（除海文大桥入口、文琼高速清澜互通入口、文昌动车站外）进行全封闭管制，在海文大桥入口、文琼高速清澜互通入口处设立查验卡口，本市以外的人员和车辆一律劝返，本市人员和车辆要减少出入，确需出入的实施强制卫生检疫措施，发现发热、咳嗽症状人员及时报告，就地筛查后，移送至指定地点隔离观察14天。'
        },
        {
          province: '宁夏',
          roadCondition: '京藏高速公路惠农匝道收费站出口、黄渠桥收费站出口、石嘴山收费站出口、四十里店收费站出口、鸣沙收费站出口，石银高速石嘴山至平罗联络线沙湖北收费站出口，银昆高速灵武西收费站出口、石沟驿收费站出口、综合保税区匝道出入口，青银高速高沙窝西收费站，乌玛高速公路黄羊滩收费站出口、闽宁收费站出口，泾华高速泾河源收费站出入口临时进行封闭，待疫情结束后恢复通行。'
        },
        {
          province: '西藏',
          roadCondition: '无官方消息'
        },
        {
          province: '新疆',
          roadCondition: '无官方消息'
        },
        {
          province: '青海',
          roadCondition: '"因疫情防控，G6京藏高速马平路段马场垣匝道收费站出入口ETC车道暂时封闭，人工车道正常通行。\n' +
          '671公里668米(八字墩省界处)因新型冠状病毒疫情影响进行双向封闭，青海省境内公里671公里668米至780公里645米（八字墩省界至祁连县城）路段正常通行，请前往甘肃省的车辆提前择路绕行\n' +
          '绕行线路：去往甘肃的车辆请沿G213策磨线行驶至祁连县城-沿S302峨祁线至峨堡-沿G227张孟线至西宁-沿G6京藏高速至兰州。\n' +
          '673公里至486米至678公里311米（当金山垭口至山脚)路段因新型冠状病毒疫情影响双向封闭，过往车辆请择路绕行，绕行路线:甘肃去往新疆方向车辆请绕行甘肃境内公路或经G6京藏高速转G315线通行。\n' +
          '甘肃去往青海境内车辆请走G6京藏高速。\n' +
          'G215线，93公里至300米至93公里390米(扁都口）路段因新型冠状病毒疫情影响双向封闭，过往车辆请绕行，绕行路线：请甘肃方向车辆绕行兰州-G6京藏高速-民和-西宁-G227-门源。\n' +
          '1098公里500米至1195公里492米(友谊桥青川届)因新型冠状病毒疫情影响双向封闭，凡从甘肃、四川方向进入青海省境内的车辆一律绕行G6(京藏)公路。\n' +
          '66公里至97公里300米（岗子口至加定镇）路段因新型冠状病毒疫情影响双向封闭，过往车辆请绕行。\n' +
          '绕行路线：去往甘肃至互助车辆绕行兰州-G6京藏高速-民和-西宁-S101(西宁高速）线(宁互一级）公路-互助-G341-甘禅口，反向相同。\n' +
          'G345线，安巴拉垭口至不冻泉段，K21+280处（安巴拉山垭口，电字桩号K3499+114）因新型冠状病毒疫情影响，四川与青海交界处双向暂时封闭，过往车辆禁止通行。\n' +
          'G347线，3027公里819米至3052公里817米(久治至阿坝分水岭)因新型冠状病毒疫情影响双向封闭，凡从甘肃、四川方向进入青海省境内的车辆一律绕行G6(京藏)公路。\n' +
          'S105线，0公里至21公里959米(格尔木东至格尔木北）路段因新型冠状病毒疫情影响双向封闭，过往车辆请绕行，绕行路线：G215线—滨河路-长江路-G109线-格尔木东，去往格尔木北：格尔木东-G109线-长江路-滨河路-G215线-格尔木北。\n' +
          'S301线，0公里（二指哈拉山省界处)因新型冠状病毒疫情影响进行双向封闭，青海省境内0公里至132公里（二指哈拉山至边麻沟）路段正常通行，请前往甘肃省的车辆提前择路绕行。"'
        },
        {
          province: '山西',
          roadCondition: '"平遥南收费站入口封闭。\n' +
          '交通管制：G2516东吕高速平榆段：平遥南收费站、分水岭收费站、云竹湖收费站出口允许有通行证的超限车辆下高速。\n' +
          'G18荣乌高速灵山段：1、凌晨1:00至-5：00浑源西、应县收费站入口实施交通管制禁止货车驶入高速。\n' +
          'G20青银高速太旧段：石太方向阳泉服务区主线管制，石太方向主线车辆绕行阳泉服务区接受防疫检查。\n' +
          'G3511菏宝高速闻合段， 北垣、阳隅、太阳收费站出入口封闭。\n' +
          'G3511菏宝高速闻垣段， 英言、华峰收费站出入口封闭。\n' +
          'G59呼北高速运宝段，圣天湖出入口封闭。\n' +
          'S5902运城绕城高速 ，运城南、东郭、常平、解州收费站出入口封闭。\n' +
          'S75侯平高速运三段， 南坡出入口封闭。\n' +
          'S87运风高速， 永济西、黄营、金井、卿头收费站出入口封闭。\n' +
          'G59呼北高速吉河段，乡宁西收费站政府相关人员在收费站出口进行车辆劝返工作。"'
        },
        {
          province: '福建',
          roadCondition: '无官方消息'
        },
        {
          province: '内蒙古',
          roadCondition: '因疫情检查需要，呼伦贝尔境内G10高速霍尔奇、查巴奇两个收费所于2020年1月26日中午12点出入口及ETC车道全部关闭，开通另行通知接到运营科通知：霍尔奇、查巴奇两站，出口封闭，入口正常通行2020年1月30日接到运营科通知：霍尔奇入口ETC暂时封闭入口人工车道正常通行2020年1月30日阿荣旗北站出入口全部封闭，无法通行，所在路线：G10绥满高速公路，所在方向：上行，起点桩号：K879+100，止点桩号：K981+200，影响程度：无影响，影响范围：102.1KM。'
        },
        {
          province: '贵州',
          roadCondition: '六盘水辖区都香高速西联线0KM-21KM、都香高速324KM-356KM（六盘水至威宁路段），因路面出现凝冻现象，实施临时交通管制。'
        },
        {
          province: '河南',
          roadCondition: '无官方消息'
        },
        {
          province: '湖北',
          roadCondition: '湖北省内12个地级市、1个自治州、3个省直辖县级市全部“封城”'
        },
        {
          province: '河南',
          roadCondition: '"交通管制：京港澳高速(G4)信阳北收费站、鸡公山收费站、灵山收费站；沪陕高速(G40)晁陂收费站、\n' +
          '唐河西收费站、唐河东收费站、查山收费站、光山收费站、信阳西收费站、仙居收费站、商城收费站、固始收费站、西九华山收费站;\n' +
          '大广高速(G45)白店收费站、寨河收费站、泼陂河收费站、新县收费站、西华收费站;\n' +
          '二广高速(G55)五朵山收费站、\n' +
          '构林收费站;呼北高速(G59)寨根收费站、寺湾收费站;许广高速(G0421)桐柏朱庄收费站;濮商高速(S21)淮滨南收费站;\n' +
          '淮内高速(S62)杨店收费站、息县北收费站、明港北收费站、邢集收费站、小茴店收费站;商南高速(S81)康楼收费站、红泥湾收费站、\n' +
          '下洼收费站、小史店收费站、上蔡北收费站、小岳寺收费站;内邓高速(S98)师岗收费站、陶营收费站;邓鄂高速(S99)彭桥收费站。"'
        },
        {
          province: '天津',
          roadCondition: '无官方消息'
        },
        {
          province: '黑龙江',
          roadCondition: '无官方消息'
        },
        {
          province: '四川',
          roadCondition: '无官方消息'
        }
      ]
    }
  },
  computed: {
    tableHeight() {
      const searchHeight = document.getElementById('search-bar')
      return window.innerHeight - 105 - searchHeight
    }
  },
  mounted() {
  },
  methods: {}
}
</script>

<style lang="scss" type="text/scss" scoped>
  .table-body /deep/ .cell {
    white-space: pre-line;
  }
</style>
