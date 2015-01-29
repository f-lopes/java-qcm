/**
 * Created by lopes_f on 1/23/2015.
 */

var teacherTypeSelectValue = "teacher";
var gradeListSelector = "#grades";
var userTypesSelector = "#userTypes";

$(document).ready(function() {

    if ($(userTypesSelector).val() == teacherTypeSelectValue) {
        $(gradeListSelector).hide();
    } else {
        $(gradeListSelector).show();
    }


    var registerListeners = function () {

        $(userTypesSelector).change(function() {
            if ($(userTypesSelector).val() == teacherTypeSelectValue) {
                $(gradeListSelector).hide();
            } else {
                $(gradeListSelector).show();
            }
        });
    }


    registerListeners();
});