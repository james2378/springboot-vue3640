<template>
	<div class="diy_edit page_student_vaccination_appointment" id="student_vaccination_appointment_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','vaccine_name') || $check_field('add','vaccine_name') || $check_field('get','vaccine_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>疫苗名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_vaccine_name" v-model="form['vaccine_name']" placeholder="请输入疫苗名称" v-if="(form['vaccine_name'] && $check_field('set','vaccine_name')) || (!form['vaccine_name'] && $check_field('add','vaccine_name'))"  :disabled="disabledObj['vaccine_name_isDisabled']"/>
							<span v-else-if="$check_field('get','vaccine_name')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','inoculation_point') || $check_field('add','inoculation_point') || $check_field('get','inoculation_point')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>接种点:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_inoculation_point" v-model="form['inoculation_point']" placeholder="请输入接种点" v-if="(form['inoculation_point'] && $check_field('set','inoculation_point')) || (!form['inoculation_point'] && $check_field('add','inoculation_point'))"  :disabled="disabledObj['inoculation_point_isDisabled']"/>
							<span v-else-if="$check_field('get','inoculation_point')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','student_number') || $check_field('add','student_number') || $check_field('get','student_number')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>学号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_student_number" :disabled="disabledObj['student_number_isDisabled']" v-model="form['student_number']" v-if="(form['student_number'] && $check_field('set','student_number')) || (!form['student_number'] && $check_field('add','student_number'))" >
								<option v-for="o in list_user_student_number" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','student_number')">{{ form['student_number'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','faculty') || $check_field('add','faculty') || $check_field('get','faculty')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>院系:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_faculty" v-model="form['faculty']" placeholder="请输入院系" v-if="(form['faculty'] && $check_field('set','faculty')) || (!form['faculty'] && $check_field('add','faculty'))"  :disabled="disabledObj['faculty_isDisabled']"/>
							<span v-else-if="$check_field('get','faculty')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','major') || $check_field('add','major') || $check_field('get','major')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>专业:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_major" v-model="form['major']" placeholder="请输入专业" v-if="(form['major'] && $check_field('set','major')) || (!form['major'] && $check_field('add','major'))"  :disabled="disabledObj['major_isDisabled']"/>
							<span v-else-if="$check_field('get','major')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','class_name') || $check_field('add','class_name') || $check_field('get','class_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>班级名称:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_class_name" v-model="form['class_name']" placeholder="请输入班级名称" v-if="(form['class_name'] && $check_field('set','class_name')) || (!form['class_name'] && $check_field('add','class_name'))"  :disabled="disabledObj['class_name_isDisabled']"/>
							<span v-else-if="$check_field('get','class_name')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','time_of_appointment') || $check_field('add','time_of_appointment') || $check_field('get','time_of_appointment')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>预约时间:
							</span>
						</div>
						<!-- 日长 -->
						<div class="diy_field diy_datetime">
							<input type="datetime-local" :disabled="disabledObj['time_of_appointment_isDisabled']" id="form_time_of_appointment" v-model="form['time_of_appointment']" placeholder="请输入预约时间" v-if="(form['time_of_appointment'] && $check_field('set','time_of_appointment')) || (!form['time_of_appointment'] && $check_field('add','time_of_appointment'))" />
							<span v-else-if="$check_field('get','time_of_appointment')">{{ form[''] }}</span>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/student_vaccination_appointment/get_obj?",
				url_add: "~/api/student_vaccination_appointment/add?",
				url_set: "~/api/student_vaccination_appointment/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"vaccine_name": "",
					"inoculation_point": "",
					"student_number": 0,
					"faculty": "",
					"major": "",
					"class_name": "",
					"time_of_appointment": "",
					"student_vaccination_appointment_id": 0,
				},

				obj: {
					"vaccine_name":'', // 疫苗名称
					"inoculation_point":'', // 接种点
					"student_number": 0, // 学号
					"faculty":'', // 院系
					"major":'', // 专业
					"class_name":'', // 班级名称
					"time_of_appointment": "1970-01-01 00:00:00",
					"student_vaccination_appointment_id": 0,
				},

				// 表单字段
				form: {
					"vaccine_name":'', // 疫苗名称
					"inoculation_point":'', // 接种点
					"student_number": 0, // 学号
					"faculty":'', // 院系
					"major":'', // 专业
					"class_name":'', // 班级名称
					"time_of_appointment": "1970-01-01 00:00:00",
					"student_vaccination_appointment_id": 0,

				},
				disabledObj:{
					"vaccine_name_isDisabled": false,
					"inoculation_point_isDisabled": false,
					"student_number_isDisabled": false,
					"faculty_isDisabled": false,
					"major_isDisabled": false,
					"class_name_isDisabled": false,
					"time_of_appointment_isDisabled": false,
				},
				// 用户列表
				list_user_student_number: [],

				// ID字段
				field: "student_vaccination_appointment_id",
			}
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_student_number() {
				// if(this.user_group !== "管理员" && this.form["student_number"] === 0) {
				//     this.form["student_number"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=学生");
				if(json.result && json.result.list){
					this.list_user_student_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_student_number(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["student_number"] = user_id
								_this.disabledObj['student_number' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="student_number") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/student_vaccination_appointment/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				this.form["time_of_appointment"] = this.$toTime(parseInt(this.form["time_of_appointment"]),"yyyy-MM-dd hh:mm:ss")
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_student_number();
			this.get_list_user_student_number();
		}
	}
</script>

<style>
</style>
