<#import "/spring.ftl" as spring />
<#escape x as x?html>
<h2>Clustered Job Configurations</h2>
<div id="job">
    <#if jobInfo??>
        <p/>
        <#assign details_url><@spring.url relativeUrl="${servletPath}/clusteredjob/${jobInfo.jobName}/"/></#assign>
        <form id="detailForm" action="${details_url}" method="POST" enctype="application/x-www-form-urlencoded">
            <ol>
                <li><label for="jobName">Job Name</label><input readonly type="text" name="jobName" id="jobName" value="${jobInfo.jobName}"></li>
                <li><label for="jobExecutors">Job Executors</label><span id="jobExecutors">${jobInfo.executors}</span></li>
                <li><label for="jobExecutions">Job Executions</label><span id="jobExecutions">${jobInfo.executions}</span></li>
                <li><label for="cronExpression">Cron Expression</label><input type="text" id="cronExpression" name="cron" value="${jobInfo.cron}"></li>
                <li><label for="maxInstances">Instance Limit</label><input type="number" id="maxInstances" name="maxInstances" value="${jobInfo.maxInstances}"></li>
                <li><label for="jobParameters">Job Parameters (key=value
                    pairs)</label><textarea id="jobParameters" name="paras"
                                            class="jobParameters"><#if jobParameters??>${jobParameters}</#if></textarea>
                </li>
                <li><label>Settings</label><input id="saveSettings" type="submit" value="Save Settings"/>
                    <input type="hidden" name="origin" value="clusteredjob"/>
                </li>
            </ol>
        </form>
        <br/>
        <p>Job Executions:</p>
        <table class="bordered-table">
            <tr>
                <th>Execution</th>
                <th>Executor</th>
                <th>Job&nbsp;ID</th>
                <th>Job&nbsp;Instance&nbsp;ID</th>
            </tr>
            <#list jobExecutions as execution>
                <#if execution_index % 2 == 0>
                    <#assign rowClass="name-sublevel1-even"/>
                <#else>
                    <#assign rowClass="name-sublevel1-odd"/>
                </#if>
                <tr class="${rowClass}">
                    <td>${execution.executionId}</a></td>
                    <td>${execution.executorId}</td>
                    <td>${execution.jobId}</td>
                    <td>${execution.jobInstanceId}</td>
                </tr>
            </#list>
        </table>

        <br/>
        <p>Job Executors:</p>
        <table class="bordered-table">
            <tr>
                <th>ID</th>
                <th>Host</th>
                <th>IP</th>
                <th>ARCH</th>
                <th>CPU%</th>
                <th>Disk%</th>
            </tr>
            <#list jobExecutors as executor>
                <#if executor_index % 2 == 0>
                    <#assign rowClass="name-sublevel1-even"/>
                <#else>
                    <#assign rowClass="name-sublevel1-odd"/>
                </#if>
                <tr class="${rowClass}">
                    <td>${executor.id}</td>
                    <td><#if executor.host??>${executor.host}</#if></td>
                    <td><#if executor.ip??>${executor.ip}</#if></td>
                    <td><#if executor.arch??>${executor.arch}</#if></td>
                    <td>${executor.cpuUsedPercent}</td>
                    <td>${executor.diskUsedPercent}</td>
                </tr>
            </#list>
        </table>
        <script type="text/javascript">
                <#assign message><@spring.messageText code="invalid.job.parameters" text="Invalid Job Parameters (use comma or new-line separator)"/></#assign>
            $.validator.addMethod('jobParameters', function (value) {
                return !value || /([\w\.-_\)\(]+=[^,\n]*[,\n])*([\w\.-_\)\(]+=[^,]*$)/m.test(value);
            }, '${message}');
            $(function () {
                $("#detailForm").validate();
            });
        </script>
    </#if>
</div>
</#escape>
