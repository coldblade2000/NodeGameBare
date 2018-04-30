def writefile(output):
    with open("Output.txt", "w") as text_file:
        text_file.write(output)

with open('lineareqbetter.java', 'r') as myfile:
    oldfile=myfile.read().replace('\n', '\\n ')

oldfile.replace(' // ', '\n// ')
print oldfile
writefile(oldfile)
