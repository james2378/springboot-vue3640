<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','leave_no') || $check_field('add','leave_no') || $check_field('set','leave_no')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="请假单号" prop="leave_no">
					<el-input id="leave_no" v-model="form['leave_no']" placeholder="请输入请假单号"
							  v-if="user_group === '管理员' || (form['leave_application_id'] && $check_field('set','leave_no')) || (!form['leave_application_id'] && $check_field('add','leave_no'))" :disabled="disabledObj['leave_no_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','leave_no')">{{form['leave_no']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学号" prop="student_number">
						<el-select v-if="user_group === '管理员' || (form['leave_application_id'] && $check_field('set','student_number')) || (!form['leave_application_id'] && $check_field('add','student_number'))" id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
							<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','student_number')" id="student_number" v-model="form['student_number']" :disabled="true">
							<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','teacher') || $check_field('add','teacher') || $check_field('set','teacher')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="教师" prop="teacher">
						<el-select v-if="user_group === '管理员' || (form['leave_application_id'] && $check_field('set','teacher')) || (!form['leave_application_id'] && $check_field('add','teacher'))" id="teacher" v-model="form['teacher']" :disabled="disabledObj['teacher_isDisabled']">
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
			<el-col v-if="user_group === '管理员' || $check_field('get','actual_days') || $check_field('add','actual_days') || $check_field('set','actual_days')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="实际天数" prop="actual_days">
					<el-input id="actual_days" v-model="form['actual_days']" placeholder="请输入实际天数"
							  v-if="user_group === '管理员' || (form['leave_application_id'] && $check_field('set','actual_days')) || (!form['leave_application_id'] && $check_field('add','actual_days'))" :disabled="disabledObj['actual_days_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','actual_days')">{{form['actual_days']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','certificate_of_cancellation_of_leave') || $check_field('add','certificate_of_cancellation_of_leave') || $check_field('set','certificate_of_cancellation_of_leave')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="销假凭证" prop="certificate_of_cancellation_of_leave">
					<el-upload :disabled="disabledObj['certificate_of_cancellation_of_leave_isDisabled']" id="certificate_of_cancellation_of_leave" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_certificate_of_cancellation_of_leave"
						:show-file-list="false" v-if="user_group === '管理员' || (form['leave_application_id'] && $check_field('set','certificate_of_cancellation_of_leave')) || (!form['leave_application_id'] && $check_field('add','certificate_of_cancellation_of_leave'))">
						<img v-if="form['certificate_of_cancellation_of_leave']" :src="$fullUrl(form['certificate_of_cancellation_of_leave'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','certificate_of_cancellation_of_leave')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['certificate_of_cancellation_of_leave'])" :preview-src-list="[$fullUrl(form['certificate_of_cancellation_of_leave'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
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
				field: "leave_application_id",
				url_add: "~/api/leave_application/add?",
				url_set: "~/api/leave_application/set?",
				url_get_obj: "~/api/leave_application/get_obj?",
				url_upload: "~/api/leave_application/upload?",

				query: {
					"leave_application_id": 0,
				},

				form: {
					"leave_no":'', // 请假单号
					"student_number": 0, // 学号
					"teacher": 0, // 教师
					"actual_days":'', // 实际天数
					"certificate_of_cancellation_of_leave":'', // 销假凭证
					"examine_state": "未审核",
					"examine_reply": "",
					"leave_application_id": 0, // ID

				},
				disabledObj:{
					"leave_no_isDisabled": false,
					"student_number_isDisabled": false,
					"teacher_isDisabled": false,
					"actual_days_isDisabled": false,
					"certificate_of_cancellation_of_leave_isDisabled": false,
				},
				// 用户列表
				list_user_student_number: [],
				// 用户列表
				list_user_teacher: [],

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
			 * 上传销假凭证
			 * @param {Object} param图片参数
			 */
			upload_certificate_of_cancellation_of_leave(param){
				this.uploadFile(param.file, "certificate_of_cancellation_of_leave");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
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
						case "教师":
							if(param["teacher"] > 0){
								param["teacher"] = this.user.user_id;
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
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/leave_application/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/leave_application/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/leave_application/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/leave_application/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/leave_application/view','get');
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
			this.get_list_user_teacher();
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
