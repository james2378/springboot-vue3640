<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','leave_no') || $check_field('add','leave_no') || $check_field('set','leave_no')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="请假单号" prop="leave_no">
					<el-input id="leave_no" v-model="form['leave_no']" placeholder="请输入请假单号"
							  v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','leave_no')) || (!form['leave_management_id'] && $check_field('add','leave_no'))" :disabled="true"></el-input>
					<div v-else-if="$check_field('get','leave_no')">{{form['leave_no']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','major') || $check_field('add','major') || $check_field('set','major')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="专业" prop="major">
					<el-input id="major" v-model="form['major']" placeholder="请输入专业"
							  v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','major')) || (!form['leave_management_id'] && $check_field('add','major'))" :disabled="disabledObj['major_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','major')">{{form['major']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','class_name') || $check_field('add','class_name') || $check_field('set','class_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="班级名称" prop="class_name">
					<el-input id="class_name" v-model="form['class_name']" placeholder="请输入班级名称"
							  v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','class_name')) || (!form['leave_management_id'] && $check_field('add','class_name'))" :disabled="disabledObj['class_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','class_name')">{{form['class_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','teacher') || $check_field('add','teacher') || $check_field('set','teacher')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="教师" prop="teacher">
						<el-select v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','teacher')) || (!form['leave_management_id'] && $check_field('add','teacher'))" id="teacher" v-model="form['teacher']" :disabled="disabledObj['teacher_isDisabled']">
							<el-option v-for="o in list_user_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','teacher')" id="teacher" v-model="form['teacher']" :disabled="true">
							<el-option v-for="o in list_user_teacher" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','leave_type') || $check_field('add','leave_type') || $check_field('set','leave_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="请假类型" prop="leave_type">
					<el-select id="leave_type" v-model="form['leave_type']"
						v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','leave_type')) || (!form['leave_management_id'] && $check_field('add','leave_type'))">
						<el-option v-for="o in list_leave_type" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','leave_type')">{{form['leave_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','days') || $check_field('add','days') || $check_field('set','days')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="天数" prop="days">
					<el-input id="days" v-model="form['days']" placeholder="请输入天数"
							  v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','days')) || (!form['leave_management_id'] && $check_field('add','days'))" :disabled="disabledObj['days_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','days')">{{form['days']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','start_time') || $check_field('add','start_time') || $check_field('set','start_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="开始时间" prop="start_time">
					<el-date-picker :disabled="disabledObj['start_time_isDisabled']" v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','start_time')) || (!form['leave_management_id'] && $check_field('add','start_time'))" id="start_time"
						v-model="form['start_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','start_time')">{{form['start_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','end_time') || $check_field('add','end_time') || $check_field('set','end_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="结束时间" prop="end_time">
					<el-date-picker :disabled="disabledObj['end_time_isDisabled']" v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','end_time')) || (!form['leave_management_id'] && $check_field('add','end_time'))" id="end_time"
						v-model="form['end_time']" type="datetime" placeholder="选择日期时间">
					</el-date-picker>
					<div v-else-if="$check_field('get','end_time')">{{form['end_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学号" prop="student_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_number(form['student_number']) }}
							<!--<el-input id="business_name" v-model="form['student_number']" placeholder="请输入学号"-->
							<!--v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','student_number')) || (!form['leave_management_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','student_number')) || (!form['leave_management_id'] && $check_field('add','student_number'))" id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
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
							  v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','faculty')) || (!form['leave_management_id'] && $check_field('add','faculty'))" :disabled="disabledObj['faculty_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','faculty')">{{form['faculty']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reason_for_leave') || $check_field('add','reason_for_leave') || $check_field('set','reason_for_leave')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="请假原因" prop="reason_for_leave">
					<el-input type="textarea" id="reason_for_leave" v-model="form['reason_for_leave']" placeholder="请输入请假原因"
						v-if="user_group === '管理员' || (form['leave_management_id'] && $check_field('set','reason_for_leave')) || (!form['leave_management_id'] && $check_field('add','reason_for_leave'))" :disabled="disabledObj['reason_for_leave_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reason_for_leave')">{{form['reason_for_leave']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else-if="$check_field('get','examine_reply')">{{form["examine_reply"]}}</div>
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
				field: "leave_management_id",
				url_add: "~/api/leave_management/add?",
				url_set: "~/api/leave_management/set?",
				url_get_obj: "~/api/leave_management/get_obj?",
				url_upload: "~/api/leave_management/upload?",

				query: {
					"leave_management_id": 0,
				},

				form: {
					"leave_no":this.$get_stamp(), // 请假单号
					"major":'', // 专业
					"class_name":'', // 班级名称
					"teacher": 0, // 教师
					"leave_type":'', // 请假类型
					"days":'', // 天数
					"start_time":'', // 开始时间
					"end_time":'', // 结束时间
					"student_number": 0, // 学号
					"faculty":'', // 院系
					"reason_for_leave":'', // 请假原因
					"examine_state": "未审核",
					"examine_reply": "",
					"leave_management_id": 0, // ID

				},
				disabledObj:{
					"leave_no_isDisabled": false,
					"major_isDisabled": false,
					"class_name_isDisabled": false,
					"teacher_isDisabled": false,
					"leave_type_isDisabled": false,
					"days_isDisabled": false,
					"start_time_isDisabled": false,
					"end_time_isDisabled": false,
					"student_number_isDisabled": false,
					"faculty_isDisabled": false,
					"reason_for_leave_isDisabled": false,
				},
				// 用户列表
				list_user_teacher: [],
				//请假类型选项列表
				list_leave_type: ['事假','病假','其他'],
				// 用户列表
				list_user_student_number: [],
				// 用户组
				group_user_student_number: "",

			}
		},
		methods: {
			/**
			 * 获取教师用户列表
			 */
			async get_list_user_teacher() {
                // if(this.user_group !== "管理员" && this.form["teacher"] === 0) {
                //     this.form["teacher"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=教师");
                if(json.result && json.result.list){
                    this.list_user_teacher = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_teacher(id){
				var obj = this.list_user_teacher.getObj({"user_id":id});
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
						case "教师":
							if(param["teacher"] > 0){
								param["teacher"] = this.user.user_id;
							}
							break;
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
				this.form["start_time"] = this.$toTime(parseInt(this.form["start_time"]),"yyyy-MM-dd hh:mm:ss")
				this.form["end_time"] = this.$toTime(parseInt(this.form["end_time"]),"yyyy-MM-dd hh:mm:ss")
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["start_time"]) > 9999){
					this.form["start_time"] = this.$toTime(parseInt(this.form["start_time"]),"yyyy-MM-dd hh:mm:ss")
				}
				if(parseInt(this.form["end_time"]) > 9999){
					this.form["end_time"] = this.$toTime(parseInt(this.form["end_time"]),"yyyy-MM-dd hh:mm:ss")
				}

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/leave_management/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/leave_management/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/leave_management/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/leave_management/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/leave_management/view','get');
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
			this.get_list_user_teacher();
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
