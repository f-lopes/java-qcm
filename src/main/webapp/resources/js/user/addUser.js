/**
 * Created by lopes_f on 1/23/2015.
 */

var teacherTypeSelectValue = "teacher";
var gradeListSelector = "#grades";
var userTypesSelector = "#userTypes";

$(document).ready(function() {

    registerListeners();

    var registerListeners = function () {

        $(userTypesSelector).change(function() {
            if ($(this).attr("value") == teacherTypeSelectValue) {
                $(gradeListSelector).hide();
            } else {
                $(gradeListSelector).show();
            }
        });
    }
});