<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="新闻资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="社区互动"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher/list', 'get')"
				:list="result_teacher_teacher_job_number"
				title="教师教师工号"
				source_table="teacher"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher/list', 'get')"
				:list="result_teacher_faculty"
				title="教师院系"
				source_table="teacher"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_student_number"
				title="学生学号"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_class_name"
				title="学生班级名称"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_faculty"
				title="学生院系"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student/list', 'get')"
				:list="result_student_major"
				title="学生专业"
				source_table="student"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/travel_report/list', 'get')"
				:list="result_travel_report_faculty"
				title="出行报备院系"
				source_table="travel_report"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/travel_report/list', 'get')"
				:list="result_travel_report_major"
				title="出行报备专业"
				source_table="travel_report"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/travel_report/list', 'get')"
				:list="result_travel_report_class_name"
				title="出行报备班级名称"
				source_table="travel_report"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/vaccine_information/list', 'get')"
				:list="result_vaccine_information_vaccine_name"
				title="疫苗信息疫苗名称"
				source_table="vaccine_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/vaccine_information/list', 'get')"
				:list="result_vaccine_information_inoculation_point"
				title="疫苗信息接种点"
				source_table="vaccine_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/vaccine_information/list', 'get')"
				:list="result_vaccine_information_whether_there_are_seedlings"
				title="疫苗信息是否有苗"
				source_table="vaccine_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_vaccination_appointment/list', 'get')"
				:list="result_student_vaccination_appointment_vaccine_name"
				title="学生接种预约疫苗名称"
				source_table="student_vaccination_appointment"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_vaccination_appointment/list', 'get')"
				:list="result_student_vaccination_appointment_faculty"
				title="学生接种预约院系"
				source_table="student_vaccination_appointment"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_vaccination_appointment/list', 'get')"
				:list="result_student_vaccination_appointment_class_name"
				title="学生接种预约班级名称"
				source_table="student_vaccination_appointment"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher_vaccination_appointment/list', 'get')"
				:list="result_teacher_vaccination_appointment_vaccine_name"
				title="教师接种预约疫苗名称"
				source_table="teacher_vaccination_appointment"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher_vaccination_appointment/list', 'get')"
				:list="result_teacher_vaccination_appointment_faculty"
				title="教师接种预约院系"
				source_table="teacher_vaccination_appointment"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/leave_management/list', 'get')"
				:list="result_leave_management_leave_no"
				title="请假管理请假单号"
				source_table="leave_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/leave_management/list', 'get')"
				:list="result_leave_management_class_name"
				title="请假管理班级名称"
				source_table="leave_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/leave_management/list', 'get')"
				:list="result_leave_management_leave_type"
				title="请假管理请假类型"
				source_table="leave_management"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/leave_application/list', 'get')"
				:list="result_leave_application_leave_no"
				title="销假申请请假单号"
				source_table="leave_application"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_teacher_teacher_job_number":[],
			"result_teacher_faculty":[],
			"result_student_student_number":[],
			"result_student_class_name":[],
			"result_student_faculty":[],
			"result_student_major":[],
			"result_travel_report_faculty":[],
			"result_travel_report_major":[],
			"result_travel_report_class_name":[],
			"result_vaccine_information_vaccine_name":[],
			"result_vaccine_information_inoculation_point":[],
			"result_vaccine_information_whether_there_are_seedlings":[],
			"result_student_vaccination_appointment_vaccine_name":[],
			"result_student_vaccination_appointment_faculty":[],
			"result_student_vaccination_appointment_class_name":[],
			"result_teacher_vaccination_appointment_vaccine_name":[],
			"result_teacher_vaccination_appointment_faculty":[],
			"result_leave_management_leave_no":[],
			"result_leave_management_class_name":[],
			"result_leave_management_leave_type":[],
			"result_leave_application_leave_no":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取社区互动
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取teacher_job_number
	 */
	get_teacher_teacher_job_number(){
		this.$get("~/api/teacher/get_list?like=0", { page: 1, size: 10, "teacher_job_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_teacher_job_number = json.result.list;
			result_teacher_teacher_job_number.map(o => o.title = o['teacher_job_number'])
	  			this.result_teacher_teacher_job_number = result_teacher_teacher_job_number
		 	}
		});
	},
	/**
	 * 获取faculty
	 */
	get_teacher_faculty(){
		this.$get("~/api/teacher/get_list?like=0", { page: 1, size: 10, "faculty": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_faculty = json.result.list;
			result_teacher_faculty.map(o => o.title = o['faculty'])
	  			this.result_teacher_faculty = result_teacher_faculty
		 	}
		});
	},
	/**
	 * 获取student_number
	 */
	get_student_student_number(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "student_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_student_number = json.result.list;
			result_student_student_number.map(o => o.title = o['student_number'])
	  			this.result_student_student_number = result_student_student_number
		 	}
		});
	},
	/**
	 * 获取class_name
	 */
	get_student_class_name(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "class_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_class_name = json.result.list;
			result_student_class_name.map(o => o.title = o['class_name'])
	  			this.result_student_class_name = result_student_class_name
		 	}
		});
	},
	/**
	 * 获取faculty
	 */
	get_student_faculty(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "faculty": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_faculty = json.result.list;
			result_student_faculty.map(o => o.title = o['faculty'])
	  			this.result_student_faculty = result_student_faculty
		 	}
		});
	},
	/**
	 * 获取major
	 */
	get_student_major(){
		this.$get("~/api/student/get_list?like=0", { page: 1, size: 10, "major": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_major = json.result.list;
			result_student_major.map(o => o.title = o['major'])
	  			this.result_student_major = result_student_major
		 	}
		});
	},
	/**
	 * 获取faculty
	 */
	get_travel_report_faculty(){
		this.$get("~/api/travel_report/get_list?like=0", { page: 1, size: 10, "faculty": this.query.word }, (json) => {
		  if (json.result) {
			var result_travel_report_faculty = json.result.list;
			result_travel_report_faculty.map(o => o.title = o['faculty'])
	  			this.result_travel_report_faculty = result_travel_report_faculty
		 	}
		});
	},
	/**
	 * 获取major
	 */
	get_travel_report_major(){
		this.$get("~/api/travel_report/get_list?like=0", { page: 1, size: 10, "major": this.query.word }, (json) => {
		  if (json.result) {
			var result_travel_report_major = json.result.list;
			result_travel_report_major.map(o => o.title = o['major'])
	  			this.result_travel_report_major = result_travel_report_major
		 	}
		});
	},
	/**
	 * 获取class_name
	 */
	get_travel_report_class_name(){
		this.$get("~/api/travel_report/get_list?like=0", { page: 1, size: 10, "class_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_travel_report_class_name = json.result.list;
			result_travel_report_class_name.map(o => o.title = o['class_name'])
	  			this.result_travel_report_class_name = result_travel_report_class_name
		 	}
		});
	},
	/**
	 * 获取vaccine_name
	 */
	get_vaccine_information_vaccine_name(){
		this.$get("~/api/vaccine_information/get_list?like=0", { page: 1, size: 10, "vaccine_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_vaccine_information_vaccine_name = json.result.list;
			result_vaccine_information_vaccine_name.map(o => o.title = o['vaccine_name'])
	  			this.result_vaccine_information_vaccine_name = result_vaccine_information_vaccine_name
		 	}
		});
	},
	/**
	 * 获取inoculation_point
	 */
	get_vaccine_information_inoculation_point(){
		this.$get("~/api/vaccine_information/get_list?like=0", { page: 1, size: 10, "inoculation_point": this.query.word }, (json) => {
		  if (json.result) {
			var result_vaccine_information_inoculation_point = json.result.list;
			result_vaccine_information_inoculation_point.map(o => o.title = o['inoculation_point'])
	  			this.result_vaccine_information_inoculation_point = result_vaccine_information_inoculation_point
		 	}
		});
	},
	/**
	 * 获取whether_there_are_seedlings
	 */
	get_vaccine_information_whether_there_are_seedlings(){
		this.$get("~/api/vaccine_information/get_list?like=0", { page: 1, size: 10, "whether_there_are_seedlings": this.query.word }, (json) => {
		  if (json.result) {
			var result_vaccine_information_whether_there_are_seedlings = json.result.list;
			result_vaccine_information_whether_there_are_seedlings.map(o => o.title = o['whether_there_are_seedlings'])
	  			this.result_vaccine_information_whether_there_are_seedlings = result_vaccine_information_whether_there_are_seedlings
		 	}
		});
	},
	/**
	 * 获取vaccine_name
	 */
	get_student_vaccination_appointment_vaccine_name(){
		this.$get("~/api/student_vaccination_appointment/get_list?like=0", { page: 1, size: 10, "vaccine_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_vaccination_appointment_vaccine_name = json.result.list;
			result_student_vaccination_appointment_vaccine_name.map(o => o.title = o['vaccine_name'])
	  			this.result_student_vaccination_appointment_vaccine_name = result_student_vaccination_appointment_vaccine_name
		 	}
		});
	},
	/**
	 * 获取faculty
	 */
	get_student_vaccination_appointment_faculty(){
		this.$get("~/api/student_vaccination_appointment/get_list?like=0", { page: 1, size: 10, "faculty": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_vaccination_appointment_faculty = json.result.list;
			result_student_vaccination_appointment_faculty.map(o => o.title = o['faculty'])
	  			this.result_student_vaccination_appointment_faculty = result_student_vaccination_appointment_faculty
		 	}
		});
	},
	/**
	 * 获取class_name
	 */
	get_student_vaccination_appointment_class_name(){
		this.$get("~/api/student_vaccination_appointment/get_list?like=0", { page: 1, size: 10, "class_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_vaccination_appointment_class_name = json.result.list;
			result_student_vaccination_appointment_class_name.map(o => o.title = o['class_name'])
	  			this.result_student_vaccination_appointment_class_name = result_student_vaccination_appointment_class_name
		 	}
		});
	},
	/**
	 * 获取vaccine_name
	 */
	get_teacher_vaccination_appointment_vaccine_name(){
		this.$get("~/api/teacher_vaccination_appointment/get_list?like=0", { page: 1, size: 10, "vaccine_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_vaccination_appointment_vaccine_name = json.result.list;
			result_teacher_vaccination_appointment_vaccine_name.map(o => o.title = o['vaccine_name'])
	  			this.result_teacher_vaccination_appointment_vaccine_name = result_teacher_vaccination_appointment_vaccine_name
		 	}
		});
	},
	/**
	 * 获取faculty
	 */
	get_teacher_vaccination_appointment_faculty(){
		this.$get("~/api/teacher_vaccination_appointment/get_list?like=0", { page: 1, size: 10, "faculty": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_vaccination_appointment_faculty = json.result.list;
			result_teacher_vaccination_appointment_faculty.map(o => o.title = o['faculty'])
	  			this.result_teacher_vaccination_appointment_faculty = result_teacher_vaccination_appointment_faculty
		 	}
		});
	},
	/**
	 * 获取leave_no
	 */
	get_leave_management_leave_no(){
		this.$get("~/api/leave_management/get_list?like=0", { page: 1, size: 10, "leave_no": this.query.word }, (json) => {
		  if (json.result) {
			var result_leave_management_leave_no = json.result.list;
			result_leave_management_leave_no.map(o => o.title = o['leave_no'])
	  			this.result_leave_management_leave_no = result_leave_management_leave_no
		 	}
		});
	},
	/**
	 * 获取class_name
	 */
	get_leave_management_class_name(){
		this.$get("~/api/leave_management/get_list?like=0", { page: 1, size: 10, "class_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_leave_management_class_name = json.result.list;
			result_leave_management_class_name.map(o => o.title = o['class_name'])
	  			this.result_leave_management_class_name = result_leave_management_class_name
		 	}
		});
	},
	/**
	 * 获取leave_type
	 */
	get_leave_management_leave_type(){
		this.$get("~/api/leave_management/get_list?like=0", { page: 1, size: 10, "leave_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_leave_management_leave_type = json.result.list;
			result_leave_management_leave_type.map(o => o.title = o['leave_type'])
	  			this.result_leave_management_leave_type = result_leave_management_leave_type
		 	}
		});
	},
	/**
	 * 获取leave_no
	 */
	get_leave_application_leave_no(){
		this.$get("~/api/leave_application/get_list?like=0", { page: 1, size: 10, "leave_no": this.query.word }, (json) => {
		  if (json.result) {
			var result_leave_application_leave_no = json.result.list;
			result_leave_application_leave_no.map(o => o.title = o['leave_no'])
	  			this.result_leave_application_leave_no = result_leave_application_leave_no
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_teacher_teacher_job_number();
		this.get_teacher_faculty();
		this.get_student_student_number();
		this.get_student_class_name();
		this.get_student_faculty();
		this.get_student_major();
		this.get_travel_report_faculty();
		this.get_travel_report_major();
		this.get_travel_report_class_name();
		this.get_vaccine_information_vaccine_name();
		this.get_vaccine_information_inoculation_point();
		this.get_vaccine_information_whether_there_are_seedlings();
		this.get_student_vaccination_appointment_vaccine_name();
		this.get_student_vaccination_appointment_faculty();
		this.get_student_vaccination_appointment_class_name();
		this.get_teacher_vaccination_appointment_vaccine_name();
		this.get_teacher_vaccination_appointment_faculty();
		this.get_leave_management_leave_no();
		this.get_leave_management_class_name();
		this.get_leave_management_leave_type();
		this.get_leave_application_leave_no();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_teacher_teacher_job_number();
	  this.get_teacher_faculty();
	  this.get_student_student_number();
	  this.get_student_class_name();
	  this.get_student_faculty();
	  this.get_student_major();
	  this.get_travel_report_faculty();
	  this.get_travel_report_major();
	  this.get_travel_report_class_name();
	  this.get_vaccine_information_vaccine_name();
	  this.get_vaccine_information_inoculation_point();
	  this.get_vaccine_information_whether_there_are_seedlings();
	  this.get_student_vaccination_appointment_vaccine_name();
	  this.get_student_vaccination_appointment_faculty();
	  this.get_student_vaccination_appointment_class_name();
	  this.get_teacher_vaccination_appointment_vaccine_name();
	  this.get_teacher_vaccination_appointment_faculty();
	  this.get_leave_management_leave_no();
	  this.get_leave_management_class_name();
	  this.get_leave_management_leave_type();
	  this.get_leave_application_leave_no();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
