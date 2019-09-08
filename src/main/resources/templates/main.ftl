<#import "parts/common.ftl" as c>

<@c.page>
    <a href="/main">
        <h1>Kino Search</h1>
    </a>

    <form action="/main" method="post">
        <input type="date" class="form-control" id="date" name="filterDate" onchange="$('#send').click();" required>
        <button type="submit" id="send"></button>
    </form>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Позиция</th>
            <td>Рейтинг</td>
            <td>Оригинальное название</td>
            <td>Год</td>
            <td>Количество проголосовавших людей</td>
        </tr>
        </thead>
        <tbody>
            <#list movies! as movie>
            <tr>
                <th scope="row">${movie.topPosition}</th>
                <td>${movie.rating}</td>
                <td>${movie.originalName}</td>
                <td>${movie.movieYear}</td>
                <td>${movie.voted}</td>
            </tr>
            </#list>
        </tbody>
    </table>
</@c.page>