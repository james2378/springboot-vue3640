<template>
	<div class="diy_edit page_teacher_vaccination_appointment" id="teacher_vaccination_appointment_edit">
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
					<div v-if="$check_field('set','teacher_job_number') || $check_field('add','teacher_job_number') || $check_field('get','teacher_job_number')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>教师工号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_teacher_job_number" :disabled="disabledObj['teacher_job_number_isDisabled']" v-model="form['teacher_job_number']" v-if="(form['teacher_job_number'] && $check_field('set','teacher_job_number')) || (!form['teacher_job_number'] && $check_field('add','teacher_job_number'))" >
								<option v-for="o in list_user_teacher_job_number" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','teacher_job_number')">{{ form['teacher_job_number'] }}</span>
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
					<div v-if="$check_field('set','inoculation_time') || $check_field('add','inoculation_time') || $check_field('get','inoculation_time')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>接种时间:
							</span>
						</div>
						<!-- 日长 -->
						<div class="diy_field diy_datetime">
							<input type="datetime-local" :disabled="disabledObj['inoculation_time_isDisabled']" id="form_inoculation_time" v-model="form['inoculation_time']" placeholder="请输入接种时间" v-if="(form['inoculation_time'] && $check_field('set','inoculation_time')) || (!form['inoculation_time'] && $check_field('add','inoculation_time'))" />
							<span v-else-if="$check_field('get','inoculation_time')">{{ form[''] }}</span>
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
				url_get_obj: "~/api/teacher_vaccination_appointment/get_obj?",
				url_add: "~/api/teacher_vaccination_appointment/add?",
				url_set: "~/api/teacher_vaccination_appointment/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"vaccine_name": "",
					"inoculation_point": "",
					"teacher_job_number": 0,
					"faculty": "",
					"inoculation_time": "",
					"teacher_vaccination_appointment_id": 0,
				},

				obj: {
					"vaccine_name":'', // 疫苗名称
					"inoculation_point":'', // 接种点
					"teacher_job_number": 0, // 教师工号
					"faculty":'', // 院系
					"inoculation_time": "1970-01-01 00:00:00",
					"teacher_vaccination_appointment_id": 0,
				},

				// 表单字段
				form: {
					"vaccine_name":'', // 疫苗名称
					"inoculation_point":'', // 接种点
					"teacher_job_number": 0, // 教师工号
					"faculty":'', // 院系
					"inoculation_time": "1970-01-01 00:00:00",
					"teacher_vaccination_appointment_id": 0,

				},
				disabledObj:{
					"vaccine_name_isDisabled": false,
					"inoculation_point_isDisabled": false,
					"teacher_job_number_isDisabled": false,
					"faculty_isDisabled": false,
					"inoculation_time_isDisabled": false,
				},
				// 用户列表
				list_user_teacher_job_number: [],

				// ID字段
				field: "teacher_vaccination_appointment_id",
			}
		},
		methods: {
			/**
			 * 获取教师用户列表
			 */
			async get_list_user_teacher_job_number() {
				// if(this.user_group !== "管理员" && this.form["teacher_job_number"] === 0) {
				//     this.form["teacher_job_number"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=教师");
				if(json.result && json.result.list){
					this.list_user_teacher_job_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_teacher_job_number(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=教师");
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
								_this.form["teacher_job_number"] = user_id
								_this.disabledObj['teacher_job_number' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="teacher_job_number") {
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
				this.$post("~/api/teacher_vaccination_appointment/upload?", form, (res) => {
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
				this.form["inoculation_time"] = this.$toTime(parseInt(this.form["inoculation_time"]),"yyyy-MM-dd hh:mm:ss")
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
			this.get_user_session_teacher_job_number();
			this.get_list_user_teacher_job_number();
		}
	}
</script>

<style>
</style>
