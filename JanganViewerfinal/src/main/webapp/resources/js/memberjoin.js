
	$(function() {
		$("#joinForm").submit(
				function() {
					if ($("#pw").val() !== $("#pw2").val()) {
						alert("비밀번호가 다릅니다.");
						$("#pw").val("").focus();
						$("#pw2").val("");
						return false;
					} else if ($("#pw").val().length < 8) {
						alert("비밀번호는 8자 이상으로 설정해야 합니다.");
						$("#pw").val("").focus();
						return false;
					} else if ($.trim($("#pw").val()) !== $("#pw").val()
							|| $.trim($("#email").val()) !== $("#email").val()
							|| $.trim($("#id").val()) !== $("#id").val()) {
						alert("공백은 입력이 불가능합니다.");
						return false;
					}
				})

		$("#id").keyup(function() {
			$.ajax({
				url : "./check_id.do",
				type : "POST",
				data : {
					id : $("#id").val()
				},
				success : function(result) {
					if (result == 1) {
						$("#id_email_check").html("중복된 아이디가 있습니다.");
						$("#joinBtn").attr("disabled", "disabled");
					} else {
						$("#id_email_check").html("");
						$("#joinBtn").removeAttr("disabled");
					}
				},
			})
		});

		$("#email").keyup(function() {
			$.ajax({
				url : "./check_email.do",
				type : "POST",
				data : {
					email : $("#email").val()
				},
				success : function(result) {
					if (result == 1) {
						$("#id_email_check").html("중복된 이메일이 있습니다.");
					} else {
						$("#id_email_check").html("");
					}
				},
			})
		});
	})


	$(function() {
		$("#find_id_btn").click(function() {
			location.href = './find_id_form.do';
		})
	})
