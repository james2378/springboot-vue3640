<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学号" prop="student_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_number(form['student_number']) }}
							<!--<el-input id="business_name" v-model="form['student_number']" placeholder="请输入学号"-->
							<!--v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','student_number')) || (!form['travel_report_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','student_number')) || (!form['travel_report_id'] && $check_field('add','student_number'))" id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
								<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','student_number')" id="student_number" v-model="form['student_number']" :disabled="true">
								<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
							<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','faculty') || $check_field('add','faculty') || $check_field('set','faculty')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="院系" prop="faculty">
					<el-input id="faculty" v-model="form['faculty']" placeholder="请输入院系"
							  v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','faculty')) || (!form['travel_report_id'] && $check_field('add','faculty'))" :disabled="disabledObj['faculty_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','faculty')">{{form['faculty']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','major') || $check_field('add','major') || $check_field('set','major')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="专业" prop="major">
					<el-input id="major" v-model="form['major']" placeholder="请输入专业"
							  v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','major')) || (!form['travel_report_id'] && $check_field('add','major'))" :disabled="disabledObj['major_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','major')">{{form['major']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','class_name') || $check_field('add','class_name') || $check_field('set','class_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="班级名称" prop="class_name">
					<el-input id="class_name" v-model="form['class_name']" placeholder="请输入班级名称"
							  v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','class_name')) || (!form['travel_report_id'] && $check_field('add','class_name'))" :disabled="disabledObj['class_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','class_name')">{{form['class_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','temperature') || $check_field('add','temperature') || $check_field('set','temperature')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="体温" prop="temperature">
					<el-input id="temperature" v-model="form['temperature']" placeholder="请输入体温"
							  v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','temperature')) || (!form['travel_report_id'] && $check_field('add','temperature'))" :disabled="disabledObj['temperature_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','temperature')">{{form['temperature']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','travel_code') || $check_field('add','travel_code') || $check_field('set','travel_code')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="行程码" prop="travel_code">
					<el-upload :disabled="disabledObj['travel_code_isDisabled']" id="travel_code" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_travel_code"
						:show-file-list="false" v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','travel_code')) || (!form['travel_report_id'] && $check_field('add','travel_code'))">
						<img v-if="form['travel_code']" :src="$fullUrl(form['travel_code'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','travel_code')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['travel_code'])" :preview-src-list="[$fullUrl(form['travel_code'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','health_code') || $check_field('add','health_code') || $check_field('set','health_code')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="健康码" prop="health_code">
					<el-upload :disabled="disabledObj['health_code_isDisabled']" id="health_code" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_health_code"
						:show-file-list="false" v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','health_code')) || (!form['travel_report_id'] && $check_field('add','health_code'))">
						<img v-if="form['health_code']" :src="$fullUrl(form['health_code'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','health_code')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['health_code'])" :preview-src-list="[$fullUrl(form['health_code'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','nucleic_acid_report') || $check_field('add','nucleic_acid_report') || $check_field('set','nucleic_acid_report')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="核酸报告" prop="nucleic_acid_report">
					<el-upload :disabled="disabledObj['nucleic_acid_report_isDisabled']" id="nucleic_acid_report" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_nucleic_acid_report"
						:show-file-list="false" v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','nucleic_acid_report')) || (!form['travel_report_id'] && $check_field('add','nucleic_acid_report'))">
						<img v-if="form['nucleic_acid_report']" :src="$fullUrl(form['nucleic_acid_report'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','nucleic_acid_report')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['nucleic_acid_report'])" :preview-src-list="[$fullUrl(form['nucleic_acid_report'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','out_time') || $check_field('add','out_time') || $check_field('set','out_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="外出时间" prop="out_time">
					<el-date-picker :disabled="disabledObj['out_time_isDisabled']" v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','out_time')) || (!form['travel_report_id'] && $check_field('add','out_time'))" id="out_time"
						v-model="form['out_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','out_time')">{{form['out_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','trip') || $check_field('add','trip') || $check_field('set','trip')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="行程" prop="trip">
					<el-input type="textarea" id="trip" v-model="form['trip']" placeholder="请输入行程"
						v-if="user_group === '管理员' || (form['travel_report_id'] && $check_field('set','trip')) || (!form['travel_report_id'] && $check_field('add','trip'))" :disabled="disabledObj['trip_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','trip')">{{form['trip']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "travel_report_id",
				url_add: "~/api/travel_report/add?",
				url_set: "~/api/travel_report/set?",
				url_get_obj: "~/api/travel_report/get_obj?",
				url_upload: "~/api/travel_report/upload?",

				query: {
					"travel_report_id": 0,
				},

				form: {
					"student_number": 0, // 学号
					"faculty":'', // 院系
					"major":'', // 专业
					"class_name":'', // 班级名称
					"temperature":'', // 体温
					"travel_code":'', // 行程码
					"health_code":'', // 健康码
					"nucleic_acid_report":'', // 核酸报告
					"out_time":'', // 外出时间
					"trip":'', // 行程
					"travel_report_id": 0, // ID

				},
				disabledObj:{
					"student_number_isDisabled": false,
					"faculty_isDisabled": false,
					"major_isDisabled": false,
					"class_name_isDisabled": false,
					"temperature_isDisabled": false,
					"travel_code_isDisabled": false,
					"health_code_isDisabled": false,
					"nucleic_acid_report_isDisabled": false,
					"out_time_isDisabled": false,
					"trip_isDisabled": false,
				},
				// 用户列表
				list_user_student_number: [],
				// 用户组
				group_user_student_number: "",

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
			/**
			 * 获取学生用户组
			 */
			async get_group_user_student_number() {
				this.form["student_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					this.group_user_student_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_student_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_student_number.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="student_number") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_student_number(id){
				var obj = this.list_user_student_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 上传行程码
			 * @param {Object} param图片参数
			 */
			upload_travel_code(param){
				this.uploadFile(param.file, "travel_code");
			},
			/**
			 * 上传健康码
			 * @param {Object} param图片参数
			 */
			upload_health_code(param){
				this.uploadFile(param.file, "health_code");
			},
			/**
			 * 上传核酸报告
			 * @param {Object} param图片参数
			 */
			upload_nucleic_acid_report(param){
				this.uploadFile(param.file, "nucleic_acid_report");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学号":
							if(param["student_number"] > 0){
								param["student_number"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							if(dbKey === "charging_standard"){
								this.form['charging_rules'] = form[dbKey];
								this.disabledObj['charging_rules_isDisabled'] = true;
							};
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				this.form["out_time"] = this.$toTime(parseInt(this.form["out_time"]),"yyyy-MM-dd hh:mm:ss")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["out_time"]) > 9999){
					this.form["out_time"] = this.$toTime(parseInt(this.form["out_time"]),"yyyy-MM-dd hh:mm:ss")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/travel_report/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/travel_report/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/travel_report/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/travel_report/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/travel_report/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_student_number();
			this.get_group_user_student_number();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
